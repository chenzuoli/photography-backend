package pet.petcage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.common.Constant;
import pet.petcage.dto.BluetoothDTO;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.Device;
import pet.petcage.service.DeviceService;
import pet.petcage.util.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/3/24 16:20
 * description: 蓝牙设备控制器
 */
@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    Constant constant;

    @RequestMapping(value = "/get_device_location", method = RequestMethod.POST)
    public ResultDTO getDeviceLocations() {
        List<Device> deviceLocations = deviceService.getDeviceLocations();
        JSONArray result = new JSONArray();
        for (Device device : deviceLocations) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", device.getId());
            jsonObject.put("device_id", device.getDevice_id());
            jsonObject.put("latitude", device.getLatitude());
            jsonObject.put("longitude", device.getLongitude());
            result.add(jsonObject);
        }
        return ResultDTO.ok(result);
    }

    @RequestMapping("/add_device")
    public ResultDTO addDevice(@RequestParam String device_id,
                               @RequestParam String device_name,
                               @RequestParam String accessory_ids,
                               @RequestParam String phone,
                               @RequestParam String latitude,
                               @RequestParam String longitude,
                               @RequestParam String kwh,
                               @RequestParam String size) {
        int result = deviceService.addDevice(device_id, device_name, accessory_ids, phone, latitude, longitude, kwh, size);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加设备失败");
        }
    }

    /**
     * 根据自增id获取设备信息
     *
     * @param id 设备表自增主键id
     * @return 设备信息ResultDTO
     */
    @RequestMapping(value = "/get_device_info", method = RequestMethod.POST)
    public ResultDTO getDeviceInfo(@RequestParam("id") String id) {
        Device deviceInfo = deviceService.getDeviceInfo(id);
        if (deviceInfo == null) {
            return ResultDTO.fail("未找到该设备");
        }
        return ResultDTO.ok(deviceInfo);
    }

    /**
     * 获取蓝牙设备指令集
     *
     * @param dvname 设备名称
     * @return 设备指令集
     */
    @RequestMapping(value = "/get_device_bluetooth_command", method = RequestMethod.POST)
    public ResultDTO getDeviceBluetoothCommand(@RequestParam("dvname") String dvname) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dvname", dvname);
        params.put("acctoken", constant.getAcctoken());
        String response = HttpUtil.sendPost(constant.getDev_command_list(), params);
        BluetoothDTO bluetoothDTO = new BluetoothDTO();
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            bluetoothDTO.setSta(jsonObject.getString("sta"));
            bluetoothDTO.setMsg(jsonObject.getJSONObject("msg"));
        } catch (Exception e){
            e.printStackTrace();
            return ResultDTO.fail("获取设备命令集失败");
        }
        return ResultDTO.ok(bluetoothDTO);
    }

    /**
     * 获取蓝牙设备服务id
     *
     * @return 服务id
     */
    @RequestMapping(value = "/get_service_id")
    public ResultDTO getServiceId() {
        String service_id = constant.getService_id();
        if (service_id == null || service_id.trim().equals("")) {
            return ResultDTO.fail("获取服务id失败");
        } else {
            return ResultDTO.ok(service_id);
        }
    }

    /**
     * 获取蓝牙设备特征id
     *
     * @return 特征id
     */
    @RequestMapping(value = "/get_characteristic_id")
    public ResultDTO getCharacteristicId() {
        String characteristic_id = constant.getCharacteristic_id();
        if (characteristic_id == null || characteristic_id.trim().equals("")) {
            return ResultDTO.fail("获取特征id失败");
        } else {
            return ResultDTO.ok(characteristic_id);
        }
    }

    @RequestMapping(value = "/get_device_power_volume")
    public ResultDTO getDevicePowerVolume(@RequestParam("dvname") String dvname,
                                          @RequestParam("encryptedStr") String encryptedStr) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dvname", dvname);
        params.put("acctoken", constant.getAcctoken());
        params.put("str", encryptedStr);
        String response = HttpUtil.sendPost(constant.getDecrypt_open_url(), params);
        String kwh = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            kwh = jsonObject.getString("msg").split("]")[0].split("=")[1];
            this.updateDevicePowerVolume(dvname, kwh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("get device power volume response: " + response);
        return ResultDTO.ok(kwh);
    }

    @RequestMapping(value = "/update_device_power_volume", method = RequestMethod.POST)
    public ResultDTO updateDevicePowerVolume(@RequestParam("device_name") String device_name,
                                             @RequestParam("kwh") String kwh) {
        int result = 0;
        if (kwh != null && kwh.length() != 0) {
            result = deviceService.updateDevicePowerVolume(device_name, kwh);
        }
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("更新设备电量失败");
        }
    }
}
