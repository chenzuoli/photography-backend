package pet.petcage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:param.properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constant {
    private String select_user_by_id_sql; // 通过id查user sql
    private String select_user_by_phone_sql; // 通过phone查user sql
    private String insert_user_sql; // 添加用户sql
    private String update_user_by_id_sql; // 根据id更新user sql
    private String update_user_by_phone_sql; // 根据phone更新user sql
    private String wx_app_id; // wx appid
    private String wx_app_secret; // wx secret
    private String access_url; // 微信获取session key，openid的url
    private String access_token_url; // 微信获取accessToken的url
    private String sms_url; // 榛子短信url
    private String sms_app_id; // 榛子短信appid
    private String sms_app_secret; // 榛子短信secret
    private String dev_command_list; // 获取蓝牙设备命令集
    private String decrypt_open_url; // 解密蓝牙设备开锁结果
    private String decrypt_close_url; // 解密蓝牙设备关闭结果
    private String acctoken; // 蓝牙设备用户token
    private String unlimited_qrcode; // 微信获取无限小程序码url
    private String qrcode_path; // 微信小程序码存储路径
    private String service_id; // 蓝牙设备服务id
    private String characteristic_id; // 蓝牙设备特征id
    private String qiniu_access_key; // 七牛云ak
    private String qiniu_secret_key; // 七牛云sk
    private String qiniu_bucket_name; // 七牛云bucket名称
    private String qiniu_domain_of_bucket; // 七牛云域名映射
    private String avatar_path; // 头像本地存放路径
}
