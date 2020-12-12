package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dao.UserPetRepository;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.UserPet;
import pet.petcage.service.UserPetService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 15:58
 * description: 用户宠物表控制器
 */
@RestController
public class UserPetController {

    @Autowired
    UserPetService userPetService;
    @Autowired
    UserPetRepository userPetRepository;

    /**
     * 更新宠物信息
     *
     * @param contact     联系人
     * @param pet_type    宠物类型
     * @param variety     宠物品种
     * @param nick_name   宠物昵称
     * @param gender      宠物性别
     * @param birthday    出生日期
     * @param avatar_url  七牛云头像url
     * @param description 描述
     * @param id          宠物id
     * @return ResultDTO，data中int值可判断更新成功与失败
     */
    @RequestMapping(value = "/update_user_pet", method = RequestMethod.POST)
    public ResultDTO updateUserPet(@RequestParam("contact") String contact,
                                   @RequestParam("pet_type") String pet_type,
                                   String variety,
                                   String nick_name,
                                   String gender,
                                   String birthday,
                                   String avatar_url,
                                   String description,
                                   @RequestParam("id") String id) {
        int result = userPetService.updateUserPet(contact, pet_type, variety, nick_name, gender, birthday, avatar_url, description, id);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("更新失败。");
        }
    }

    /**
     * 获取用户所有的宠物
     *
     * @param open_id 用户open_id
     * @return ResultDTO，data中包含用户所有的宠物信息UserPet对象
     */
    @RequestMapping(value = "/get_user_pets", method = RequestMethod.POST)
    public ResultDTO getUserPets(@RequestParam("open_id") String open_id) {
        List<UserPet> userPets = userPetService.getUserPets(open_id);
        if (userPets.size() > 0) {
            return ResultDTO.ok(userPets);
        } else {
            return ResultDTO.fail("查询无结果");
        }
    }

    /**
     * 查询宠物
     *
     * @param id 宠物id
     * @return ResultDTO，data中包含一个UserPet宠物对象
     */
    @RequestMapping(value = "/get_pet_info", method = RequestMethod.POST)
    public ResultDTO getPetInfo(@RequestParam("id") String id) {
        UserPet userPet = userPetService.getPetInfo(id);
        if (userPet == null) {
            return ResultDTO.fail("查询无结果");
        } else {
            return ResultDTO.ok(userPet);
        }
    }

    @RequestMapping(value = "/add_user_pet", method = RequestMethod.POST)
    public ResultDTO addUserPet(@RequestParam("open_id") String open_id,
                                @RequestParam("contact") String contact,
                                @RequestParam("pet_type") String pet_type,
                                String variety,
                                String nick_name,
                                String gender,
                                String birthday,
                                String avatar_url,
                                String description) {
        UserPet userPet = new UserPet();
        userPet.setOpen_id(open_id);
        userPet.setContact(contact);
        userPet.setPet_type(pet_type);
        userPet.setVariety(variety);
        userPet.setNick_name(nick_name);
        userPet.setGender(gender);
        userPet.setBirthday(birthday);
        userPet.setAvatar_url(avatar_url);
        userPet.setDescription(description);
//        int result = userPetService.addUserPet(userPet);
//        if (result > 0) {
//            return ResultDTO.ok(userPet.getId());
//        } else {
//            return ResultDTO.fail("添加宠物失败。");
//        }
        UserPet save = userPetRepository.save(userPet);
        if (save != null) {
            // 返回插入的pet_id
            return ResultDTO.ok(userPet.getId());
        } else {
            return ResultDTO.fail("添加宠物失败。");
        }
    }

    @RequestMapping(value = "/delete_user_pet", method = RequestMethod.POST)
    public ResultDTO deleteUserPet(@RequestParam("id") String id) {
        int result = userPetService.deleteUserPet(id);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("删除宠物失败。");
        }
    }

}
