package pet.photography.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.common.Constant;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.User;
import pet.photography.error.CommonErrorCode;
import pet.photography.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 返回对象
 * //@RestController
 * <p>
 * 返回的内容是去根目录寻找相应的jsp文件
 * //@Controller
 */
@RestController
public class UserController {

    @Autowired
    Constant constant;
    @Autowired
    UserService userService;
    @Autowired
    AppInfoController appInfoController;

    /**
     * 获取用户信息
     *
     * @param phone 用户手机号
     * @return 返回用户对象
     */
    @RequestMapping(value = "/get_user_by_phone", method = RequestMethod.POST)
    public ResultDTO getUserByPhone(@RequestParam String phone) {
        User user = userService.getById(phone);
        return ResultDTO.ok(user);
    }

    /**
     * 用户名、密码方式登录检验
     *
     * @param phone 用户手机号
     * @param pwd   用户密码
     * @return 用户名密码是否匹配
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultDTO login(@RequestParam String phone, @RequestParam String pwd) {
        boolean is_match = userService.loginCheck(phone, pwd);
        ResultDTO resultDTO = appInfoController.accessToken();
        if (is_match && resultDTO.getStatus() == 200) {
            // 更新用户token
            updateUserToken(phone, resultDTO.getData().toString());
            return ResultDTO.ok(resultDTO.getData());
        } else {
            return ResultDTO.fail("登录失败");
        }
    }

    private void updateUserToken(String phone, String token) {
        userService.updateUserToken(phone, token);
    }

    @RequestMapping(value = "/login_app", method = RequestMethod.POST)
    public ResultDTO loginApp(@RequestParam String open_id, @RequestParam String pwd) {
        boolean is_match = userService.appLoginCheck(open_id, pwd);
        String token = UUID.randomUUID().toString();
        if (is_match) {
            // 更新用户token
            userService.updateAppUserToken(open_id, token);
            return ResultDTO.ok(token);
        } else {
            return ResultDTO.fail("登录失败");
        }
    }

    /**
     * 发送短信
     *
     * @param phone       手机号
     * @param httpSession session
     * @return boolean 发送成功、失败
     */
    @RequestMapping("/sms_code")
    public ResultDTO smsCode(@RequestParam String phone, HttpSession httpSession) {
        try {
            ZhenziSmsClient client = new ZhenziSmsClient(
                    constant.getSms_url(),
                    constant.getSms_app_id(),
                    constant.getSms_app_secret());
            HashMap<String, String> params = new HashMap<>();
            String code = String.valueOf(100000 + new Random().nextInt(899999));
            params.put("message", "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");
            params.put("number", phone);
            String result = client.send(params);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                return ResultDTO.fail("发送验证码失败");
            }
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            json = new JSONObject();
            json.put("phone", phone);
            json.put("code", code);
            json.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            httpSession.setAttribute("code", json);
            return ResultDTO.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail("发送验证码失败");
    }

    /**
     * 注册验证验证码
     *
     * @param phone       手机号
     * @param code        前端传回验证码
     * @param httpSession session
     * @return 验证结果
     */
    @RequestMapping(value = "/register_code", method = RequestMethod.POST)
    public ResultDTO smsCodeCheck(@RequestParam String phone,
                                  @RequestParam String code,
                                  HttpSession httpSession) {
        ResultDTO resultDTO = null;
//        JSONObject json = (JSONObject) httpSession.getAttribute("code");
//        if (System.currentTimeMillis() - 5 * 60 * 1000 > Long.parseLong(json.getString("createTime"))) {
//            resultDTO = ResultDTO.fail(CommonErrorCode.CODE_EXPIRED);
//        } else {
//            if (phone.equals(json.getString("phone")) && code.equals(json.getString("code"))) {
//                resultDTO = ResultDTO.ok("注册成功");
//            } else {
//                resultDTO = ResultDTO.fail(CommonErrorCode.CODE_INVALID);
//            }
//        }
        resultDTO = ResultDTO.ok("注册成功");
        return resultDTO;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultDTO register(@RequestParam("phone") String phone,
                              @RequestParam("pwd") String pwd) {
        ResultDTO resultDTO = appInfoController.accessToken();
        int result = userService.register(phone, pwd, resultDTO.getData().toString());
        if (result > 0) {
            // 更新用户token
            updateUserToken(phone, resultDTO.getData().toString());
            return ResultDTO.ok(resultDTO.getData());
        } else {
            return ResultDTO.fail("登录失败");
        }
    }

    @RequestMapping(value = "/register_app", method = RequestMethod.POST)
    public ResultDTO registerApp(@RequestParam("open_id") String open_id,
                                 @RequestParam("password") String password) {
        List<User> users = userService.getUserByOpenid(open_id);
        if (users.size() > 0) {
            return ResultDTO.fail("该用户已存在");
        } else {
            String token = userService.registerApp(open_id, password);
            return "注册失败".equals(token) ? ResultDTO.fail("注册失败") : ResultDTO.ok(token);
        }
    }

    /**
     * 注册app用户，默认nick_name，avatar_url，不过依然从app传过来的
     *
     * @param open_id  用户id
     * @param password 密码
     * @return resultdto
     */
    @RequestMapping(value = "/register_app_user", method = RequestMethod.POST)
    public ResultDTO registerAppUser(@RequestParam("open_id") String open_id,
                                     @RequestParam("password") String password,
                                     @RequestParam("nick_name") String nick_name,
                                     @RequestParam("avatar_url") String avatar_url) {
        List<User> users = userService.getUserByOpenid(open_id);
        if (users.size() > 0) {
            return ResultDTO.fail("该用户已存在");
        } else {
            String token = userService.registerAppUser(open_id, password, nick_name, avatar_url);
            return "注册失败".equals(token) ? ResultDTO.fail("注册失败") : ResultDTO.ok(token);
        }
    }

    /**
     * 更新用户密码
     *
     * @param nick_name   用户昵称
     * @param pwd         用户新密码
     * @param phone       用户手机号
     * @param code        更改密码手机验证码
     * @param httpSession session
     * @return 更新结果
     */
    @RequestMapping(value = "/update_pass", method = RequestMethod.POST)
    public ResultDTO updatePass(@RequestParam("nick_name") String nick_name,
                                @RequestParam("pwd") String pwd,
                                @RequestParam("phone") String phone,
                                @RequestParam("code") String code,
                                HttpSession httpSession) {
        JSONObject json = (JSONObject) httpSession.getAttribute("code");
        ResultDTO resultDTO = null;
        if (System.currentTimeMillis() - 5 * 60 * 1000 > Long.parseLong(json.getString("createTime"))) {
            resultDTO = ResultDTO.fail(CommonErrorCode.CODE_EXPIRED);
        } else {
            if (phone.equals(json.getString("phone")) && code.equals(json.getString("code"))) {
                int flag = userService.updatePass(nick_name, pwd, phone);
                if (flag > 0) {
                    resultDTO = ResultDTO.ok("更新密码成功");
                } else {
                    resultDTO = ResultDTO.fail("更新密码失败");
                }
            } else {
                resultDTO = ResultDTO.fail(CommonErrorCode.CODE_INVALID);
            }
        }
        return resultDTO;
    }

    /**
     * 获取用户钱包信息
     *
     * @param open_id 用户open_id
     * @return 返回用户钱包信息
     */
    @RequestMapping(value = "/get_user_wallet", method = RequestMethod.POST)
    public ResultDTO getUserWallet(@RequestParam("open_id") String open_id) {
        User userWallet = userService.getUserWallet(open_id);
        if (userWallet == null) {
            return ResultDTO.fail("获取用户钱包失败");
        }
        userWallet.setPhone("");
        userWallet.setUnion_id("");
        userWallet.setPwd("");
        return ResultDTO.ok(userWallet);
    }

    @RequestMapping(value = "/get_user_by_open_id", method = RequestMethod.POST)
    public ResultDTO getUserByOpenid(@RequestParam("open_id") String open_id) {
        List<User> users = userService.getUserByOpenid(open_id);
        if (users.size() == 0) {
            return ResultDTO.fail("未查询到user");
        } else {
            return ResultDTO.ok(users.get(0));
        }
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ResultDTO addUser(User user) {
        int result = userService.addUser(user);
        if (result > 0) {
            return ResultDTO.ok("添加用户成功");
        } else {
            return ResultDTO.fail("添加用户失败");
        }
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public ResultDTO updateUser(User user) {
        int result = userService.updateUser(user);
        if (result > 0) {
            return ResultDTO.ok("更新用户成功");
        } else {
            return ResultDTO.fail("更新用户失败");
        }
    }

    @RequestMapping(value = "/update_user_info", method = RequestMethod.POST)
    public ResultDTO updateUserInfo(@RequestParam("avatar_url") String avatar_url,
                                    @RequestParam("nick_name") String nick_name,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("province") String province,
                                    @RequestParam("city") String city,
                                    @RequestParam("open_id") String open_id) {
        int result = userService.updateUserInfo(avatar_url, nick_name, gender, phone, province, city, open_id);
        if (result > 0) {
            return ResultDTO.ok("更新用户成功");
        } else {
            return ResultDTO.fail("更新用户失败");
        }
    }

    @RequestMapping(value = "/update_app_pass", method = RequestMethod.POST)
    public ResultDTO updateAppUserPass(@RequestParam("open_id") String open_id,
                                       @RequestParam("pwd") String pwd) {
        int result = userService.updateAppUserPass(open_id, pwd);
        return result > 0 ? ResultDTO.ok("更新密码成功") : ResultDTO.fail("更新密码失败");
    }

    @RequestMapping(value = "/get_app_user", method = RequestMethod.GET)
    public ResultDTO getAppUser(@RequestParam("open_id") String open_id) {
        User appUser = userService.getAppUser(open_id);
        return appUser == null ? ResultDTO.fail("无此用户") : ResultDTO.ok(appUser);
    }

    @RequestMapping(value = "/update_nick_name", method = RequestMethod.POST)
    public ResultDTO updateNickName(@RequestParam("open_id") String open_id,
                                    @RequestParam("nick_name") String nick_name) {
        int result = userService.updateNickName(open_id, nick_name);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

}
