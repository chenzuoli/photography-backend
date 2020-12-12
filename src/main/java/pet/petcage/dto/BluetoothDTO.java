package pet.petcage.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by user chenzuoli on 2020/3/29 09:59
 * description: 蓝牙设备连接反馈
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BluetoothDTO {
    private String sta;
    private JSONObject msg;
    private String number;
}
