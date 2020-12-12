package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.UserRepository;
import pet.petcage.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    UserRepository repo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public User getById(String id) {
        System.out.println("get user by id params: " + id);
        return repo.findUserByPhone(id);
    }

    public boolean loginCheck(String phone, String pwd) {
        System.out.println("login check params: " + phone + "," + pwd);
        List<User> users = repo.loginCheck(phone, pwd);
        System.out.println(users);
        return users.size() != 0;
    }

    public int updatePass(String nick_name, String pwd, String phone) {
        String update_time = dateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println("update password params: " + nick_name + "," + pwd + "," + update_time + "," + phone);
        return repo.updatePass(nick_name, pwd, update_time, phone);
    }

    public User getUserWallet(String open_id) {
        System.out.println("get user wallet params: " + open_id);
        return repo.getUserWallet(open_id);
    }

    public List<User> getUserByOpenid(String open_id) {
        System.out.println("get user by open_id params: " + open_id);
        return repo.getUserByOpenid(open_id);
    }

    public int addUser(User user) {
        System.out.println("add user parmas: " + user);
        return repo.addUser(
                user.getPhone(),
                user.getOpen_id(),
                user.getUnion_id(),
                UUID.randomUUID().toString(),
                "1",
                "0",
                dateFormat.format(new Date(System.currentTimeMillis())),
                "3000-12-31 00:00:00",
                user.getToken(),
                user.getCountry(),
                user.getProvince(),
                user.getCity(),
                user.getAvatar_url(),
                user.getGender(),
                user.getNick_name(),
                user.getLanguage()
        );
    }

    public int register(String phone, String pwd, String token) {
        System.out.println("register params: " + phone + "," + pwd + "," + token);
        return repo.register(phone,
                pwd,
                "1",
                "0",
                dateFormat.format(new Date(System.currentTimeMillis())),
                "3000-12-31 00:00:00",
                token);
    }

    public int updateUser(User user) {
        System.out.println("update user params: " + user);
        String update_time = dateFormat.format(new Date(System.currentTimeMillis()));
        return repo.updateUser(
                user.getUnion_id(),
                user.getToken(),
                user.getCountry(),
                user.getProvince(),
                user.getCity(),
                user.getAvatar_url(),
                user.getGender(),
                user.getNick_name(),
                user.getLanguage(),
                update_time,
                user.getOpen_id()
        );
    }

    public List<User> getUserByToken(String token) {
        System.out.println("get user by token params: " + token);
        return repo.getUserByToken(token);
    }

    public int updateUserToken(String phone, String token) {
        System.out.println("update user token: " + phone + "," + token);
        return repo.updateUserToken(phone, token);
    }

    public int updateUserInfo(String avatar_url, String nick_name, String gender, String phone, String province, String city, String open_id) {
        System.out.println("update user info: " + avatar_url + "," + nick_name + "," + gender + "," + phone + "," + province + "," + city + "," + open_id);
        return repo.updateUserInfo(avatar_url, nick_name, gender, phone, province, city, open_id);
    }

}
