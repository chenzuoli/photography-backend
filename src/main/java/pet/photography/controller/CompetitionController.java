package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Competition;
import pet.photography.service.CompetitionService;

import java.util.List;
import java.util.UUID;

/**
 * Created by user chenzuoli on 2020/12/13 07:55
 * description: 比赛控制器
 */
@RestController
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    @RequestMapping(value = "/add_competition", method = RequestMethod.POST)
    public ResultDTO addCompetition(@RequestParam("name") String name,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("img_url") String img_url,
                                    @RequestParam("condition") String condition,
                                    @RequestParam("start_date") String start_date,
                                    @RequestParam("end_date") String end_date) {
        UUID competition_id = UUID.randomUUID();
        int result = competitionService.addCompetition(competition_id.toString(), name, subject, img_url, condition, start_date, end_date);
        return result > 0 ? ResultDTO.ok("插入成功") : ResultDTO.fail("插入失败");
    }

    @RequestMapping(value = "/update_name", method = RequestMethod.POST)
    public ResultDTO updateName(@RequestParam("name") String name,
                                @RequestParam("competition_id") String competition_id) {
        int result = competitionService.updateName(name, competition_id);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

    @RequestMapping(value = "/update_subject", method = RequestMethod.POST)
    public ResultDTO updateSubject(@RequestParam("subject") String subject,
                                   @RequestParam("competition_id") String competition_id) {
        int result = competitionService.updateSubject(subject, competition_id);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

    @RequestMapping(value = "/update_condition", method = RequestMethod.POST)
    public ResultDTO updateCodition(@RequestParam("condition") String condition,
                                    @RequestParam("competition_id") String competition_id) {
        int result = competitionService.updateCondition(condition, competition_id);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

    @RequestMapping(value = "/update_competition", method = RequestMethod.POST)
    public ResultDTO updateCompetition(@RequestParam("name") String name,
                                       @RequestParam("subject") String subject,
                                       @RequestParam("img_url") String img_url,
                                       @RequestParam("condition") String condition,
                                       @RequestParam("start_date") String start_date,
                                       @RequestParam("end_date") String end_date,
                                       @RequestParam("competition_id") String competition_id) {
        int result = competitionService.updateCompetition(name, subject, img_url, condition, start_date, end_date, competition_id);
        return result > 0 ? ResultDTO.ok("更新成功") : ResultDTO.fail("更新失败");
    }

    @RequestMapping(value = "/get_competition", method = RequestMethod.GET)
    public ResultDTO getCompetition(@RequestParam("competition_id") String competition_id) {
        Competition competition = competitionService.getCompetition(competition_id);
        return competition != null ? ResultDTO.ok(competition) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_competitions", method = RequestMethod.GET)
    public ResultDTO getCompetitions() {
        List<Competition> competitions = competitionService.getCompetitions();
        return competitions.size() != 0 ? ResultDTO.ok(competitions) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_latest_competition", method = RequestMethod.GET)
    public ResultDTO getLatestCompetition() {
        Competition latestCompetition = competitionService.getLatestCompetition();
        return latestCompetition != null ? ResultDTO.ok(latestCompetition) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_latest_competitions", method = RequestMethod.GET)
    public ResultDTO getLatestCompetitions(@RequestParam("num") int num) {
        List<Competition> latestCompetition = competitionService.getLatestCompetition(num);
        return latestCompetition != null ? ResultDTO.ok(latestCompetition) : ResultDTO.fail("未找到");
    }

    @RequestMapping(value = "/get_competitions_by_ids", method = RequestMethod.GET)
    public ResultDTO getCompetitions(@RequestParam("competition_ids") List<String> competition_ids) {
        List<Competition> competitions = competitionService.getCompetitions(competition_ids);
        return competitions.size() > 0 ? ResultDTO.ok(competitions) : ResultDTO.fail("未找到");
    }
}
