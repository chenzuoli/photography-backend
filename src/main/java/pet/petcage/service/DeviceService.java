package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.DeviceRepository;
import pet.petcage.entity.Device;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/3/24 16:25
 * description: 设备服务类
 */
@Service
public class DeviceService extends BaseService<Device> {
    @Autowired
    DeviceRepository devRepo;

    @Override
    public Device getById(String id) {
        return null;
    }

    public List<Device> getDeviceLocations() {
        return devRepo.deviceLocations();
    }

    /**
     * 添加设备信息
     *
     * @param device_id   设备id
     * @param device_name 设备名称
     * @param latitude    设备所在径度
     * @param longitude   设备所在纬度
     * @param kwh         设备所剩电量
     * @return 返回设备信息
     */
    public int addDevice(String device_id, String device_name, String accessory_ids, String phone, String latitude, String longitude, String kwh, String size) {
        System.out.println("params: " + device_id + "," + device_name + "," + accessory_ids + "," + phone + "," + latitude + "," + longitude + "," + kwh + "," + size);
        return devRepo.addDevice(device_id, device_name, accessory_ids, phone, latitude, longitude, kwh, "2", size);
    }

    /**
     * 通过自增id获取设备信息
     *
     * @param id 设备表自增id
     * @return Device对象
     */
    public Device getDeviceInfo(String id) {
        System.out.println("params: " + id);
        return devRepo.getDeviceById(id);
    }

    public int updateDevicePowerVolume(String device_name, String kwh) {
        System.out.println("update device power volume params: device_name=" + device_name + ",kwh=" + kwh);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String update_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return devRepo.updateDevicePowerVolume(device_name, kwh, update_time);
    }


}
