package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pet.photography.entity.Competition;

import javax.transaction.Transactional;

/**
 * Created by user chenzuoli on 2020/12/12 22:06
 * description: 比赛工厂类
 */
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into competition (name, subject, condition, start_date, end_date) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    Competition addCompetition(String name, String subject, String condition, String start_date, String end_date);

    @Transactional
    @Modifying
    @Query(value = "update competition set name = ?1 where competition_id = ?2", nativeQuery = true)
    Competition updateName(String name, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set subject = ?1 where competition_id = ?2", nativeQuery = true)
    Competition updateSubject(String subject, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set condition = ?1 where competition_id = ?2", nativeQuery = true)
    Competition updateCondition(String condition, String competition_id);

    @Transactional
    @Modifying
    @Query(value = "update competition set name = ?1, subject = ?2, condition = ?3, start_date = ?4, end_date = ?5 where competition_id = ?6")
    Competition updateCompetition(String name, String subject, String condition, String start_date, String end_date, String competition_id);

    @Query(value = "select * from competition where competition_id = ?1", nativeQuery = true)
    Competition getCompetition(String competition_id);

}
