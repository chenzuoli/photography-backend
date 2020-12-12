package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.common.Constant;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.PetcageOrder;
import pet.petcage.service.PetcageOrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/1 17:26
 * description: 订单控制器
 */
@RestController
public class PetcageOrderController {
    @Autowired
    Constant constant;
    @Autowired
    PetcageOrderService petcageOrderService;

    @RequestMapping(value = "/get_petcage_order_by_phone", method = RequestMethod.POST)
    public ResultDTO getPetcageOrderByPhone(@RequestParam("phone") String phone) {
        List<PetcageOrder> orders = petcageOrderService.getPetcageOrderByPhone(phone);
        return ResultDTO.ok(orders);
    }

    @RequestMapping(value = "/get_petcage_order_by_open_id", method = RequestMethod.POST)
    public ResultDTO getPetcageOrderByOpenId(@RequestParam("open_id") String open_id) {
        List<PetcageOrder> orders = petcageOrderService.getPetcageOrderByOpenId(open_id);
        return ResultDTO.ok(orders);
    }

    /**
     * 添加订单
     *
     * @param order_id  订单id
     * @param phone     宠物联系人
     * @param open_id   用户open_id
     * @param device_id 宠笼设备id
     * @return 添加成功或失败 >0表示成功
     */
    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    public ResultDTO add_order(@RequestParam("order_id") String order_id,
                               @RequestParam("phone") String phone,
                               @RequestParam("open_id") String open_id,
                               @RequestParam("device_id") String device_id,
                               @RequestParam("pet_id") String pet_id) {
        System.out.println("params: order_id=" + order_id + ",phone=" + phone + ",open_id=" + open_id + ",device_id=" + device_id + ",pet_id=" + pet_id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curr_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        int result = petcageOrderService.add_order(order_id, phone, open_id, false, device_id, pet_id, curr_time);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加订单失败");
        }
    }


    /**
     * 订单关闭，已结算完成
     *
     * @param amount   订单金额
     * @param open_id  用户open_id
     * @param order_id 订单id
     * @return >0表示关闭成功 <=0表示关闭失败
     */
    @RequestMapping(value = "/close_order", method = RequestMethod.POST)
    public ResultDTO close_order(@RequestParam("amount") String amount,
                                 @RequestParam("open_id") String open_id,
                                 @RequestParam("order_id") String order_id) {
        System.out.println("params: " + amount + "," + open_id + "," + order_id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curr_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        int result = petcageOrderService.close_order(curr_time, amount, open_id, order_id);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("关闭订单失败");
        }
    }

    /**
     * 获取用户所有服务订单
     *
     * @param open_id 用户open_id
     * @return ResultDTO，data中包含PetcageOrder数组
     */
    @RequestMapping(value = "/get_petcage_order", method = RequestMethod.POST)
    public ResultDTO getPetcageOrder(@RequestParam("open_id") String open_id) {
        List<PetcageOrder> petcageOrder = petcageOrderService.getPetcageOrder(open_id);
        return ResultDTO.ok(petcageOrder);
    }

    @RequestMapping(value = "/get_petcage_status", method = RequestMethod.POST)
    public ResultDTO getPetcageStatus(@RequestParam("device_id") String device_id) {
        List<PetcageOrder> petcageStatus = petcageOrderService.getPetcageStatus(device_id);
        if (petcageStatus.size() == 0) {
            return ResultDTO.ok("无人使用");
        } else {
            return ResultDTO.fail("正在使用中");
        }
    }


}
