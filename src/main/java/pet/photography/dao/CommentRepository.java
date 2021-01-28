package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Comment;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:26
 * description: 评论工厂类
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query(value = "select * from comment where photography_id = ?1", nativeQuery = true)
    List<Comment> get_comments(String photography_id);

    @Query(value = "select * from comment where photography_id = ?1 and open_id = ?2", nativeQuery = true)
    List<Comment> get_comment_by_id(String photography_id, String open_id);
}
