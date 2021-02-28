package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.UserRepository;
import pet.photography.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService extends BaseService<User> {

    Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository repo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public User getById(String id) {
        logger.info("get user by id params: " + id);
        return repo.findUserByPhone(id);
    }

    public boolean loginCheck(String phone, String pwd) {
        logger.info("login check params: " + phone + "," + pwd);
        List<User> users = repo.loginCheck(phone, pwd);
        logger.info(users.toString());
        return users.size() != 0;
    }

    public boolean appLoginCheck(String open_id, String pwd) {
        logger.info("login check params: " + open_id + "," + pwd);
        List<User> users = repo.appLoginCheck(open_id, pwd);
        logger.info(users.toString());
        return users.size() != 0;
    }

    public int updatePass(String nick_name, String pwd, String phone) {
        String update_time = dateFormat.format(new Date(System.currentTimeMillis()));
        logger.info("update password params: " + nick_name + "," + pwd + "," + update_time + "," + phone);
        return repo.updatePass(nick_name, pwd, update_time, phone);
    }

    public User getUserWallet(String open_id) {
        logger.info("get user wallet params: " + open_id);
        return repo.getUserWallet(open_id);
    }

    public List<User> getUserByOpenid(String open_id) {
        logger.info("get user by open_id params: " + open_id);
        return repo.getUserByOpenid(open_id);
    }

    public int addUser(User user) {
        logger.info("add user parmas: " + user);
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
        logger.info("register params: " + phone + "," + pwd + "," + token);
        return repo.register(phone,
                pwd,
                "1",
                "0",
                dateFormat.format(new Date(System.currentTimeMillis())),
                "3000-12-31 00:00:00",
                token);
    }

    public int updateUser(User user) {
        logger.info("update user params: " + user);
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
        logger.info("get user by token params: " + token);
        return repo.getUserByToken(token);
    }

    public void updateUserToken(String phone, String token) {
        logger.info("update user token: " + phone + "," + token);
        repo.updateUserToken(phone, token);
    }

    public int updateUserInfo(String avatar_url, String nick_name, String gender, String phone, String province, String city, String open_id) {
        logger.info("update user info: " + avatar_url + "," + nick_name + "," + gender + "," + phone + "," + province + "," + city + "," + open_id);
        return repo.updateUserInfo(avatar_url, nick_name, gender, phone, province, city, open_id);
    }

    public String registerApp(String open_id, String pwd) {
        logger.info("register app user account: " + open_id + ", pwd: " + pwd);
        String token = UUID.randomUUID().toString();
        int result = repo.registerApp(open_id, pwd, token);
        return result > 0 ? token : "注册失败";
    }

    public String registerAppUser(String open_id, String pwd, String nick_name, String avatar_url) {
        logger.info("register app user account: " + open_id + ", pwd: " + pwd + ", nick_name: " + nick_name + ", avatar_url: " + avatar_url);
        String token = UUID.randomUUID().toString();
        int result = repo.registerAppUser(open_id, pwd, token, nick_name, avatar_url);
        return result > 0 ? token : "注册失败";
    }

    public int updateAppUserToken(String open_id, String token) {
        logger.info("update app user token: " + token + ", open_id: " + open_id);
        return repo.updateAppUserToken(open_id, token);
    }

    public int updateAppUserPass(String open_id, String pwd) {
        logger.info("update app user pwd: " + pwd + ", open_id: " + open_id);
        return repo.updateAppUserPass(open_id, pwd);
    }

    public User getAppUser(String open_id) {
        logger.info("get user by open_id: " + open_id);
        return repo.getAppUser(open_id);
    }

    public int updateNickName(String open_id, String nick_name) {
        logger.info("update nick_name params: " + open_id + ", " + nick_name);
        return repo.updateNickName(open_id, nick_name);
    }
}
