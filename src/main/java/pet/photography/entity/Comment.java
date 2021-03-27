package pet.photography.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/12/22 23:03
 * description: 摄影作品评论类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "comment", schema = "photography")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String photography_id;
    private String competition_id;
    private String open_id;
    private long comment_id;
    private String comment;
    private boolean is_vote;
    private String create_time;
    private String update_time;
}
