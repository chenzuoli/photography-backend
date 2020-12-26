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
}
