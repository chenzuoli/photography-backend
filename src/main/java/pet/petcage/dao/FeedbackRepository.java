package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.Feedback;

import javax.transaction.Transactional;


/**
 * Created by user chenzuoli on 2020/3/25 11:06
 * description: 反馈信息工厂
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into feedback(order_id, open_id, feedback_type, feedback_content, satisfy_grade, pictures, latitude, longitude, petcage_id, description) values(?,?,?,?,?,?,?,?,?,?)", nativeQuery = true)
    int addFeedback(String order_id, String open_id, String feedback_type, String feedback_content, String satisfy_grade, String pictures, String latitude, String longitude, String petcage_id, String description);

}
