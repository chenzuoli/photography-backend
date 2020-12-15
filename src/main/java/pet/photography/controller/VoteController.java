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
                              @RequestParam("image_url") String image_url,
                              @RequestParam("subject") String subject,
                              @RequestParam("nick_name") String nick_name) {
        int result = voteService.addPhoto(competition_id, phone, open_id, image_url, subject, nick_name);
        return result > 0 ? ResultDTO.ok("插入成功") : ResultDTO.fail("插入失败");
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
}
