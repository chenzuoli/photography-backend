package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pet.photography.entity.Competition;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/12 22:06
 * description: 比赛工厂类
 */
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    @Transactional
    @Modifying()
    @Query(value = "insert into competition (competition_id, name, subject, img_url, `condition`, start_date, end_date) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    int addCompetition(String competition_id, String name, String subject, String img_url, String condition, String start_date, String end_date);

    @Transactional
    @Modifying
    @Query(value = "update competition set name = ?1 where competition_id = ?2", nativeQuery = true)
    int updateName(String name, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set subject = ?1 where competition_id = ?2", nativeQuery = true)
    int updateSubject(String subject, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set `condition` = ?1 where competition_id = ?2", nativeQuery = true)
    int updateCondition(String condition, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set name = ?1, subject = ?2, img_url = ?3, `condition` = ?4, start_date = ?5, end_date = ?6 where competition_id = ?7", nativeQuery = true)
    int updateCompetition(String name, String subject, String img_url, String condition, String start_date, String end_date, String competition_id);

    @Query(value = "select * from competition where competition_id = ?1", nativeQuery = true)
    Competition getCompetition(String competition_id);

    @Query(value = "select * from competition", nativeQuery = true)
    List<Competition> getCompetitions();

    @Query(value = "select * from competition order by create_time desc limit 1", nativeQuery = true)
    Competition getLatestCompetition();

}
