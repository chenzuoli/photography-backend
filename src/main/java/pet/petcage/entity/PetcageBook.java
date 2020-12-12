package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/4/11 16:14
 * description: 宠笼预定表
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "petcage_book", schema = "petcage")
public class PetcageBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String open_id;
    private String device_id;
    private String book_start_time;
    private String book_end_time;
    private String line_index;
    private String create_time;
    private String update_time;

}
