package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/3/25 11:15
 * description: 损坏类型
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "damage_type", schema = "petcage")
public class DamageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // 自增主键
    private String code; // 故障类型
    private String name; // 故障名称
    private String create_time; // 创建时间
    private String update_time; // 更新时间
}
