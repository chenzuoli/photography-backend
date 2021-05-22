package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.VoteRepository;
import pet.photography.entity.Vote;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/13 07:47
 * description: 投票服务
 */
@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    public int addPhoto(String competition_id, String phone, String open_id, String url, String type, String subject, String nick_name, String tag) {
        return voteRepository.addPhoto(competition_id, phone, open_id, url, type, subject, nick_name, tag);
    }

    public int vote(int votes, String competition_id, String open_id) {
        return voteRepository.vote(votes, competition_id, open_id);
    }

    public Vote getVote(String competition_id, String open_id) {
        return voteRepository.getVote(competition_id, open_id);
    }

    public List<Vote> getVotes(String competition_id) {
        return voteRepository.getVotes(competition_id);
    }

    public List<Vote> getPopularCompetitionWorks(int num) {
        return voteRepository.getPopularCompetitionWorks(num);
    }

    public Vote getVote(int id) {
        return voteRepository.getVote(id);
    }

    public int updateVotes(int id, int votes) {
        return voteRepository.updateVotes(id, votes);
    }

    public List<Vote> checkVote(String competition_id, String open_id) {
        return voteRepository.checkVote(competition_id, open_id);
    }
}
