package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Feedback;

import javax.transaction.Transactional;

/**
 * Created by user chenzuoli on 2021/2/16 08:47
 * description: 反馈信息接口
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into feedback(open_id, content) values (?1, ?2)", nativeQuery = true)
    int addFeedback(String open_id, String content);
}
