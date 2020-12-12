package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.UserPetRepository;
import pet.petcage.entity.UserPet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 15:57
 * description: 用户宠物表服务
 */
@Service
public class UserPetService extends BaseService<UserPet> {
    @Autowired
    UserPetRepository userPetRepository;

    @Override
    public UserPet getById(String id) {
        return null;
    }

    public int updateUserPet(String contact, String pet_type, String variety, String nick_name, String gender, String birthday, String avatar_url, String description, String id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String update_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println("update user pet params: " + contact + "," + pet_type + "," + variety + "," + nick_name + "," + gender + "," + birthday + "," + avatar_url + "," + description + "," + update_time + "," + id);
        return userPetRepository.updateUserPet(contact, pet_type, variety, nick_name, gender, birthday, avatar_url, description, update_time, id);
    }

    public List<UserPet> getUserPets(String open_id) {
        System.out.println("get user pets params: " + open_id);
        return userPetRepository.getUserPets(open_id);
    }

    public UserPet getPetInfo(String id) {
        System.out.println("get pet info params: " + id);
        return userPetRepository.getPetInfo(id);
    }

    public int addUserPet(String open_id, String contact, String pet_type, String variety, String nick_name, String gender, String birthday, String avatar_url, String description) {
        System.out.println("add user pet params: " + open_id + "," + contact + "," + pet_type + "," + variety + "," + nick_name + "," + gender + "," + birthday + "," + avatar_url + "," + description);
        return userPetRepository.addUserPet(open_id, contact, pet_type, variety, nick_name, gender, birthday, avatar_url, description);
    }

    public int deleteUserPet(String id) {
        System.out.println("delete user pet params: " + id);
        return userPetRepository.deleteUserPet(id);
    }
}
