package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pet.photography.entity.Vote;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/12 22:43
 * description: 投票工厂类
 */
public interface VoteRepository extends JpaRepository<Vote, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into vote(competition_id, phone, open_id, url, type, subject, nick_name, tag_id) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    int addPhoto(String competition_id, String phone, String open_id, String url, String type, String subject, String nick_name, String tag_id);

    @Transactional
    @Modifying
    @Query(value = "update vote set votes = ?1 where competition_id = ?2 and open_id = ?3", nativeQuery = true)
    int vote(int votes, String competition_id, String open_id);

    @Query(value = "select * from vote where competition_id = ?1 and open_id = ?2", nativeQuery = true)
    Vote getVote(String competition_id, String open_id);

    @Query(value = "select * from vote where competition_id = ?1", nativeQuery = true)
    List<Vote> getVotes(String competition_id);

    @Query(value = "select * from vote order by votes desc, create_time desc limit ?1", nativeQuery = true)
    List<Vote> getPopularCompetitionWorks(int num);

    @Query(value = "select * from vote where id = ?1", nativeQuery = true)
    Vote getVote(int id);

    @Transactional
    @Modifying
    @Query(value = "update vote set votes = ?2 where id = ?1", nativeQuery = true)
    int updateVotes(int id, int votes);

    @Transactional
    @Modifying
    @Query(value = "select * from vote where competition_id = ?1 and open_id = ?2", nativeQuery = true)
    List<Vote> checkVote(String competition_id, String open_id);

    @Query(value = "select * from vote where id = ?1", nativeQuery = true)
    Vote getVoteById(int id);

}
