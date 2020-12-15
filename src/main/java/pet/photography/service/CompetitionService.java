package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.CompetitionRepository;
import pet.photography.entity.Competition;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/13 07:39
 * description: 比赛服务
 */
@Service
public class CompetitionService {
    @Autowired
    CompetitionRepository competitionRepository;

    public int addCompetition(String competition_id, String name, String subject, String condition, String start_date, String end_date) {
        return competitionRepository.addCompetition(competition_id, name, subject, condition, start_date, end_date);
    }

    public int updateName(String name, String competition_id) {
        return competitionRepository.updateName(name, competition_id);
    }

    public int updateSubject(String subject, String competition_id) {
        return competitionRepository.updateSubject(subject, competition_id);
    }

    public int updateCondition(String condition, String competition_id) {
        return competitionRepository.updateCondition(condition, competition_id);
    }

    public int updateCompetition(String name, String subject, String condition, String start_date, String end_date, String competition_id) {
        return competitionRepository.updateCompetition(name, subject, condition, start_date, end_date, competition_id);
    }

    public Competition getCompetition(String competition_id) {
        return competitionRepository.getCompetition(competition_id);
    }

    public List<Competition> getCompetitions() {
        return competitionRepository.getCompetitions();
    }
}
