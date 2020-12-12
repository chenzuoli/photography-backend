package pet.petcage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pet.petcage.common.Constant;
import pet.petcage.dto.*;
import pet.petcage.entity.AppInfo;
import pet.petcage.entity.User;
import pet.petcage.service.AppInfoService;
import pet.petcage.service.UserService;
import pet.petcage.util.HttpUtil;
import pet.petcage.util.QiNiuCludeUtil;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by user chenzuoli on 2020/4/5 18:57
 * description: 微信小程序信息控制器
 */
@RestController
public class AppInfoController {
    @Autowired
    Constant constant;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    UserService userService;

    /**
     * 小程序获取session_key openid
     *
     * @param js_code 小程序生成的code
     * @return session_key open_id
     */
    @RequestMapping(value = "/open_id", method = RequestMethod.POST)
    public ResultDTO openId(@RequestParam("js_code") String js_code) {
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", constant.getWx_app_id());
        params.put("secret", constant.getWx_app_secret());
        params.put("js_code", js_code);
        params.put("grant_type", "authorization_code");
        String response = HttpUtil.sendPost(constant.getAccess_url(), params);
        System.out.println("response: " + response);
        SessionDTO sessionDTO = new SessionDTO();
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            sessionDTO.setOpen_id(jsonObject.getString("openid"));
            sessionDTO.setSession_key(jsonObject.getString("session_key"));
        } catch (Exception e){
            e.printStackTrace();
            return ResultDTO.fail("获取失败");
        }
        return ResultDTO.ok(sessionDTO);
    }

    /**
     * 获取小程序access token, 保存用户token到mysql数据库
     *
     * @return 请求响应
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.GET)
    public ResultDTO accessToken() {
        ResultDTO resultDTO = null;
        try {
            StringBuilder params = new StringBuilder();
            params.append("appid=").append(constant.getWx_app_id());
            params.append("&secret=").append(constant.getWx_app_secret());
            params.append("&grant_type=client_credential");
            String request_url = constant.getAccess_token_url() + "?" + params;
            System.out.println(request_url);
            String response = HttpUtil.get(request_url);
            System.out.println("get access token response: " + response);
            String token = JSONObject.parseObject(response).getString("access_token");
            resultDTO = ResultDTO.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO = ResultDTO.fail("获取失败");
        }
        return resultDTO;
    }

    /**
     * 小程序获取session access_token, 保存用户token到mysql数据库
     *
     * @param loginDTO 小程序生成的code，和用户信息rawData
     * @return ResultDTO包含token
     */
    @RequestMapping(value = "/wx_login", method = RequestMethod.GET)
    public ResultDTO wxLogin(LoginDTO loginDTO) {
        System.out.println(loginDTO);
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", constant.getWx_app_id());
        params.put("secret", constant.getWx_app_secret());
        params.put("js_code", loginDTO.getJs_code());
        params.put("grant_type", "authorization_code");
        String response = HttpUtil.sendPost(constant.getAccess_url(), params);
        System.out.println("response: " + response);

        String token = UUID.randomUUID().toString();
        User user = JSON.parseObject(loginDTO.getRawData(), User.class);
        user.setToken(token);
        JSONObject result = JSON.parseObject(response);
        user.setOpen_id(result.getString("openid"));
        System.out.println(user);

        // 更新或者插入token，记录登录状态，下次客户端请求头携带token，后端拦截器做对比，存在即放行
        if (userService.getUserByOpenid(result.getString("openid")).size() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        TokenDTO data = new TokenDTO();
        data.setToken(token);
        return ResultDTO.ok(data);
    }

    /**
     * 获取小程序码
     *
     * @param page  页面id
     * @param scene 场景值
     * @return 小程序码本地路径
     */
    @RequestMapping(value = "/get_qrcode")
    @ResponseBody
    public ResultDTO getQRCode(String page, String scene) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        System.out.println("page：" + page);
        System.out.println("scene：" + scene);
        QRCodeDTO qrCodeDTO = new QRCodeDTO();
        try {
            //获取小程序码调用API
            String url = constant.getUnlimited_qrcode() + "?access_token=" + this.accessToken().getData();
            System.out.println("get qrcode url: " + url);
            Map<String, Object> param = new HashMap();
            param.put("page", page);//小程序页面
            param.put("width", 430);
            param.put("scene", scene);//参数
            param.put("auto_color", true);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);
            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "") + ".png";
            //本地上传，路径填写自己项目路径
            File file = new File(constant.getQrcode_path() + "/" + fileName);
            System.out.println("filePath : " + file.getAbsolutePath());
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            //保存到数据表

            //返回图片路径

            qrCodeDTO.setCode_path(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久二维码URL接口异常" + e);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultDTO.ok(qrCodeDTO);
    }

    /**
     * 获取小程序码
     *
     * @param scene 参数
     * @param page  页面
     * @return 小程序码七牛云访问路径
     */
    @RequestMapping(value = "/get_mini_qrcode")
    public ResultDTO getMiniQrCode(String scene, String page) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String visit_url = "";
        try {
            String url = constant.getUnlimited_qrcode() + "?access_token=" + this.accessToken().getData();
            Map<String, Object> param = new HashMap<>();
            param.put("scene", scene);
            param.put("page", page);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            System.out.println("url:" + url + ", params:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            System.out.println("get unlimited qr code response:" + entity.getBody());
            byte[] result = entity.getBody();
            System.out.println(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);

            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "") + ".png";
            //本地上传，路径填写自己项目路径
            File file = new File(constant.getQrcode_path() + "/" + fileName);
            System.out.println("filePath : " + file.getAbsolutePath());

            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

            // 上传小程序码到七牛云
            QiNiuCludeUtil.uploadFile(file.getAbsolutePath(),
                    constant.getQiniu_access_key(),
                    constant.getQiniu_secret_key(),
                    constant.getQiniu_bucket_name());
            //私有空间
//            visit_url = QiNiuCludeUtil.getFileUrl(
//                    constant.getQiniu_domain_of_bucket(),
//                    file.getAbsolutePath(),
//                    constant.getQiniu_access_key(),
//                    constant.getQiniu_secret_key());
            // 公开空间
            visit_url = QiNiuCludeUtil.getFileUrl(
                    constant.getQiniu_domain_of_bucket(),
                    file.getAbsolutePath()
            );
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久小程序码URL接口异常");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultDTO.ok(visit_url);
    }

    @RequestMapping(value = "/getminiqrQr")
    public ResultDTO getminiqrQr(String scene, String page) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String visit_url = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + this.accessToken().getData();
            Map<String, Object> param = new HashMap<>();
            param.put("scene", scene);
            param.put("page", page);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            System.out.println("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            System.out.println(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);


            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "") + ".png";
            //本地上传，路径填写自己项目路径
            File file = new File(constant.getQrcode_path() + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            System.out.println(file.getAbsolutePath());

            // 上传小程序码到七牛云
            QiNiuCludeUtil.uploadFile(file.getAbsolutePath(),
                    constant.getQiniu_access_key(),
                    constant.getQiniu_secret_key(),
                    constant.getQiniu_bucket_name());
            //私有空间
//            visit_url = QiNiuCludeUtil.getFileUrl(
//                    constant.getQiniu_domain_of_bucket(),
//                    file.getAbsolutePath(),
//                    constant.getQiniu_access_key(),
//                    constant.getQiniu_secret_key());
            // 公开空间
            visit_url = QiNiuCludeUtil.getFileUrl(
                    constant.getQiniu_domain_of_bucket(),
                    file.getAbsolutePath()
            );
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久小程序码URL接口异常");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (visit_url == null) {
            return ResultDTO.fail("失败");
        }
        return ResultDTO.ok(visit_url);
    }


    /**
     * 获取小程序服务条例与隐私协议
     *
     * @return ResultDTO
     */
    @RequestMapping(value = "/get_service_private_content", method = RequestMethod.POST)
    public ResultDTO getServicePrivateContent() {
        AppInfo appInfo = appInfoService.getServicePrivateContent();
        if (appInfo == null) {
            return ResultDTO.fail("未获取到小程序信息");
        } else {
            return ResultDTO.ok(appInfo);
        }
    }

    /**
     * 更新小程序版本号
     *
     * @param version 小程序版本号
     * @return ResultDTO
     */
    @RequestMapping(value = "/update_version", method = RequestMethod.POST)
    public ResultDTO updateVersion(@RequestParam("version") String version) {
        int i = appInfoService.updateVersion(version);
        if (i > 0) {
            return ResultDTO.ok(i);
        } else {
            return ResultDTO.fail("更新失败");
        }
    }

    @RequestMapping(value = "/get_app_version", method = RequestMethod.POST)
    public ResultDTO getAppVersion() {
        List<String> version = appInfoService.getAppVersion();
        if (version.size() == 0) {
            return ResultDTO.fail("获取app版本号失败");
        } else {
            return ResultDTO.ok(version.get(0));
        }
    }

    @RequestMapping(value = "get_service_content", method = RequestMethod.POST)
    public ResultDTO getServiceContent() {
        String serviceContent = appInfoService.getServiceContent();
        if (serviceContent == null) {
            return ResultDTO.fail("获取服务条例失败");
        } else {
            return ResultDTO.ok(serviceContent);
        }
    }

    @RequestMapping(value = "get_private_content", method = RequestMethod.POST)
    public ResultDTO getPrivateContent() {
        String privateContent = appInfoService.getPrivateContent();
        if (privateContent == null) {
            return ResultDTO.fail("获取隐私协议失败");
        } else {
            return ResultDTO.ok(privateContent);
        }
    }

}
