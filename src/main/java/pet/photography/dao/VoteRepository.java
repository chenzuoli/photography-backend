package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pet.photography.entity.Vote;

import javax.transaction.Transactional;

/**
 * Created by user chenzuoli on 2020/12/12 22:43
 * description: 投票工厂类
 */
public interface VoteRepository extends JpaRepository<Vote, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into vote(competition_id, phone, open_id, image_url, subject, nick_name) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int addPhoto(String competition_id, String phone, String open_id, String image_url, String subject, String nick_name);

    @Transactional
    @Modifying
    @Query(value = "update vote set votes = ?1 where competition_id = ?2 and open_id = ?3", nativeQuery = true)
    int vote(int votes, String competition_id, String open_id);

    @Query(value = "select * from vote where competition_id = ?1 and open_id = ?2", nativeQuery = true)
    Vote getVote(String competition_id, String open_id);

}
