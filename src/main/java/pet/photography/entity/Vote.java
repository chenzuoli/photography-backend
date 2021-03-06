package pet.photography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user chenzuoli on 2020-12-12 21:54
 * description: 投票类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@IdClass(Vote.class)
@Table(name = "vote", schema = "photography")
public class Vote implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    private String competition_id;
    private String phone;
    @Id
    private String open_id;
    private String url;
    private String type;
    private String subject;
    private String nick_name;
    private int votes;
    private String create_time;
    private String update_time;
}
