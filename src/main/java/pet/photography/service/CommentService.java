package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.CommentRepository;
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

    public int comment(String photography_id, String open_id, long comment_id, String comment) {
        return commentRepository.comment(photography_id, open_id, comment_id, comment);
    }

    public int delete_comment(long id) {
        return commentRepository.delete_comment(id);
    }
}
