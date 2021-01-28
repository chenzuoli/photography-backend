package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Comment;
import pet.photography.service.CommentService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:38
 * description: 评论控制器
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/get_comments", method = RequestMethod.GET)
    public ResultDTO getComments(@RequestParam("photography_id") String photography_id) {
        List<Comment> comments = commentService.get_comments(photography_id);
        return ResultDTO.ok(comments);
    }

    public ResultDTO getComment(@RequestParam("photography_id") String photgraphy_id,
                                @RequestParam("open_id") String open_id) {
        List<Comment> comment_by_id = commentService.get_comment_by_id(photgraphy_id, open_id);
        return ResultDTO.ok(comment_by_id);
    }
}
