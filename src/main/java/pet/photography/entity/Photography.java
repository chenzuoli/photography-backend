package pet.photography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/12/22 23:01
 * description: 摄影作品类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "photography", schema = "photography")
public class Photography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String open_id;
    private String url;
    private String type;
    private String subject;
    private String nick_name;
    private String photographer;
    private int votes;
    private String create_time;
    private String update_time;
}
