package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.CommentRepository;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Comment;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:39
 * description: 评论服务类
 */
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> get_comments(String photography_id) {
        return commentRepository.get_comments(photography_id);
    }

    public List<Comment> get_comment_by_id(String photography_id, String open_id) {
        return commentRepository.get_comment_by_id(photography_id, open_id);
    }

    public int update_vote(boolean is_vote, String photography_id, String open_id) {
        return commentRepository.update_vote(is_vote, photography_id, open_id);
    }

    protected ResultDTO isLiked(String photography_id, String open_id) {
        List<Comment> comments = commentRepository.comments(photography_id, open_id);
        return comments.size() > 0 ? ResultDTO.ok(true) : ResultDTO.fail("不喜欢这个作品了");
    }

    public int addLike(boolean is_vote, String photography_id, String open_id) {
        // 随便点赞喜欢，点赞过得，待会来还可以点赞，当然，处于当前页面时，可以取消点赞，而且只能点赞一次
        return commentRepository.addLike(is_vote, photography_id, open_id);
    }

    public int addCommentLike(boolean is_vote, String photography_id, String open_id, int comment_id) {
        return commentRepository.addCommentLike(is_vote, photography_id, open_id, comment_id);
    }

    public int comment(String photography_id, String open_id, long comment_id, String comment) {
        return commentRepository.comment(photography_id, open_id, comment_id, comment);
    }

    public int delete_comment(long id) {
        return commentRepository.delete_comment(id);
    }

    public int addVote(String competition_id, String open_id, boolean is_vote) {
        return commentRepository.addVote(competition_id, open_id, is_vote);
    }

    public List<String> getPopularCompetition() {
        return commentRepository.getPopularCompetition();
    }
}
