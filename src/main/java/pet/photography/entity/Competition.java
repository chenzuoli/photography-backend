package pet.photography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by user chenzuoli on 2020-12-12 21:43
 * description: 比赛评比模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "competition", schema = "photography")
public class Competition {
    @Id
    private String competition_id;
    private String name;
    private String subject;
    private String img_url;
    private String condition;
    private String start_date;
    private String end_date;
    private String create_time;
    private String update_time;

}
