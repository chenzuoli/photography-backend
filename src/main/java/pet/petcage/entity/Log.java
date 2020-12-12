package pet.petcage.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/3/22 16:22
 * description: 日志表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "log", schema = "petcage")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // 自增主键
    private String phone; // 用户手机号
    private String open_id; // 用户open_id
    private String union_id; // 用户union_id
    private String longitude; // 用户径度
    private String latitude; // 用户维度
    private String page_id; // 页面id
    private String create_time; // 
    private String update_time; //
}
