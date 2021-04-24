package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Vote;
import pet.photography.service.VoteService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/13 08:25
 * description:
 */
@RestController
public class VoteController {
    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public ResultDTO addPhoto(@RequestParam("competition_id") String competition_id,
                              @RequestParam("phone") String phone,
                              @RequestParam("open_id") String open_id,
                              @RequestParam("url") String url,
                              @RequestParam("type") String type,
                              @RequestParam("subject") String subject,
                              @RequestParam("nick_name") String nick_name) {
        int result = voteService.addPhoto(competition_id, phone, open_id, url, type, subject, nick_name);
        return result > 0 ? ResultDTO.ok("添加成功") : ResultDTO.fail("添加失败");
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public ResultDTO vote(@RequestParam("votes") int votes,
                          @RequestParam("competition_id") String competition_id,
                          @RequestParam("open_id") String open_id) {
        int result = voteService.vote(votes, competition_id, open_id);
        return result > 0 ? ResultDTO.ok("投票成功") : ResultDTO.fail("投票失败");
    }

    @RequestMapping(value = "/get_vote", method = RequestMethod.GET)
    public ResultDTO getVote(@RequestParam("competition_id") String competition_id,
                             @RequestParam("open_id") String open_id) {
        Vote vote = voteService.getVote(competition_id, open_id);
        return vote != null ? ResultDTO.ok(vote) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_votes", method = RequestMethod.GET)
    public ResultDTO getVotes(@RequestParam("competition_id") String competition_id) {
        List<Vote> votes = voteService.getVotes(competition_id);
        return votes.size() != 0 ? ResultDTO.ok(votes) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_popular_competition_works", method = RequestMethod.GET)
    public ResultDTO getPopularCompetitionWorks(@RequestParam("num") int num) {
        List<Vote> popularCompetitionWorks = voteService.getPopularCompetitionWorks(num);
        return popularCompetitionWorks.size() > 0 ? ResultDTO.ok(popularCompetitionWorks) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_vote_by_id", method = RequestMethod.GET)
    public ResultDTO getVote(@RequestParam("id") int id) {
        Vote vote = voteService.getVote(id);
        return vote == null ? ResultDTO.fail("未找到") : ResultDTO.ok(vote);
    }

    @RequestMapping(value = "/update_votes", method = RequestMethod.POST)
    public ResultDTO updateVotes(@RequestParam("id") int id,
                                 @RequestParam("votes") int votes) {
        int result = voteService.updateVotes(id, votes);
        return result > 0 ? ResultDTO.ok("投票成功") : ResultDTO.fail("投票失败");
    }

    @RequestMapping(value = "/check_vote", method = RequestMethod.GET)
    public ResultDTO checkVote(@RequestParam("competition_id") String competition_id,
                               @RequestParam("open_id") String open_id) {
        List<Vote> votes = voteService.checkVote(competition_id, open_id);
        return votes.size() == 0 ? ResultDTO.ok("未参赛") : ResultDTO.fail("对不起，您已参赛。");
    }

    
}
