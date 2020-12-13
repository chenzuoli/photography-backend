package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.VoteRepository;
import pet.photography.entity.Vote;

/**
 * Created by user chenzuoli on 2020/12/13 07:47
 * description: 投票服务
 */
@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    public int addPhoto(String competition_id, String phone, String open_id, String image_url, String subject, String nick_name) {
        return voteRepository.addPhoto(competition_id, phone, open_id, image_url, subject, nick_name);
    }

    public int vote(int votes, String competition_id, String open_id) {
        return voteRepository.vote(votes, competition_id, open_id);
    }

    public Vote getVote(String competition_id, String open_id) {
        return voteRepository.getVote(competition_id, open_id);
    }
}
