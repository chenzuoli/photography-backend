package pet.photography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user chenzuoli on 2020-12-12 21:54
 * description: 投票类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "vote", schema = "photography")
public class Vote {
    @Id
    private String competition_id;
    private String phone;
    @Id
    private String open_id;
    private String image_url;
    private String subject;
    private String nick_name;
    private int votes;
    private String create_time;
    private String update_time;
}
