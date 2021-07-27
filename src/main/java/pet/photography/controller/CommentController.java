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

    @RequestMapping(value = "/get_comment_by_id", method = RequestMethod.GET)
    public ResultDTO getComment(@RequestParam("photography_id") String photgraphy_id,
                                @RequestParam("open_id") String open_id) {
        List<Comment> comment_by_id = commentService.get_comment_by_id(photgraphy_id, open_id);
        return ResultDTO.ok(comment_by_id);
    }

    @RequestMapping(value = "/update_vote", method = RequestMethod.POST)
    public ResultDTO updateVote(@RequestParam("is_vote") boolean is_vote,
                                @RequestParam("photography_id") String photography_id,
                                @RequestParam("open_id") String open_id) {
        int result = commentService.update_vote(is_vote, photography_id, open_id);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

    @RequestMapping(value = "/add_like", method = RequestMethod.POST)
    public ResultDTO addLike(@RequestParam("is_vote") boolean is_vote,
                             @RequestParam("photography_id") String photography_id,
                             @RequestParam("open_id") String open_id) {
        int result = commentService.addLike(is_vote, photography_id, open_id);
        return result > 0 ? ResultDTO.ok("喜欢成功") : ResultDTO.fail("主人出差错了，不让你喜欢了，呜呜呜...");
    }

    @RequestMapping(value = "/add_comment_like", method = RequestMethod.POST)
    public ResultDTO addCommentLike(@RequestParam("is_vote") boolean is_vote,
                                    @RequestParam("photography_id") String photography_id,
                                    @RequestParam("open_id") String open_id,
                                    @RequestParam("comment_id") int comment_id) {
        int result = commentService.addCommentLike(is_vote, photography_id, open_id, comment_id);
        return result > 0 ? ResultDTO.ok("喜欢成功") : ResultDTO.fail("主人出差错了，不让您喜欢了，呜呜呜...");
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultDTO comment(@RequestParam("photography_id") String photography_id,
                             @RequestParam("open_id") String open_id,
                             @RequestParam("comment_id") long comment_id,
                             @RequestParam("comment") String comment) {
        int result = commentService.comment(photography_id, open_id, comment_id, comment);
        return result > 0 ? ResultDTO.ok("评论成功") : ResultDTO.fail("评论失败");
    }

    @RequestMapping(value = "/delete_comment", method = RequestMethod.POST)
    public ResultDTO deleteComment(@RequestParam("id") long id) {
        int result = commentService.delete_comment(id);
        return result > 0 ? ResultDTO.ok("删除成功") : ResultDTO.fail("删除失败");
    }

    @RequestMapping(value = "/add_vote", method = RequestMethod.POST)
    public ResultDTO addVote(@RequestParam("competition_id") String competition_id,
                             @RequestParam("open_id") String open_id,
                             @RequestParam("is_vote") boolean is_vote) {
        int result = commentService.addVote(competition_id, open_id, is_vote);
        return result > 0 ? ResultDTO.ok("喜欢成功") : ResultDTO.fail("主人出差错了，不让您喜欢了，呜呜呜...");
    }

    @RequestMapping(value = "/get_popular_competitions", method = RequestMethod.GET)
    public ResultDTO getPopularCompetitions() {
        List<String> works = commentService.getPopularCompetition();
        return works.size() > 0 ? ResultDTO.ok("获取成功") : ResultDTO.fail("列表为空");
    }

}
