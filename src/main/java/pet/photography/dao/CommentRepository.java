package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:26
 * description: 评论工厂类
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query(value = "select * from comment where photography_id = ?1 order by create_time desc", nativeQuery = true)
    List<Comment> get_comments(String photography_id);

    @Query(value = "select * from comment where photography_id = ?1 and open_id = ?2 order by create_time desc", nativeQuery = true)
    List<Comment> get_comment_by_id(String photography_id, String open_id);

    @Transactional
    @Modifying
    @Query(value = "update comment set is_vote = ?1 where photography_id = ?2 and open_id = ?3", nativeQuery = true)
    int update_vote(boolean is_vote, String photography_id, String open_id);

    @Transactional
    @Modifying
    @Query(value = "insert into comment (is_vote, photography_id, open_id) values (?1, ?2, ?3)", nativeQuery = true)
    int addLike(boolean is_vote, String photography_id, String open_id);

    @Transactional
    @Modifying
    @Query(value = "insert into comment(is_vote, photography_id, open_id, comment_id) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int addCommentLike(boolean is_vote, String photography_id, String open_id, int comment_id);

    @Query(value = "select * from comment where photography_id = ?1 and open_id = ?2 order by create_time desc", nativeQuery = true)
    List<Comment> comments(String photography_id, String open_id);

    @Transactional
    @Modifying
    @Query(value = "insert into comment (photography_id, open_id, comment_id, comment) values(?1, ?2, ?3, ?4)", nativeQuery = true)
        // 随机生成id，comment_id为某条评论的id，可以对某条评论进行评论
    int comment(String photography_id, String open_id, long comment_id, String comment);

    @Transactional
    @Modifying
    @Query(value = "delete from comment where id = ?1", nativeQuery = true)
    int delete_comment(long id);

    @Transactional
    @Modifying
    @Query(value = "insert into comment(competition_id, open_id, is_vote) values (?1, ?2, ?3)", nativeQuery = true)
    int addVote(String competition_id, String open_id, boolean is_vote);

    @Query(value = "select competition_id from comment where competition_id != null group by competition_id order by count(1) desc limit 10", nativeQuery = true)
    List<String> getPopularCompetitionWorks();
}
