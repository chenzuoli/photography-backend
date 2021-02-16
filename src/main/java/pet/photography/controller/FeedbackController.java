package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.service.FeedbackService;

/**
 * Created by user chenzuoli on 2021/2/16 08:52
 * description: 反馈信息控制器
 */
@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/add_feedback", method = RequestMethod.POST)
    public ResultDTO addFeedback(@RequestParam("open_id") String open_id,
                                 @RequestParam("content") String content) {
        int result = feedbackService.addFeedback(open_id, content);
        return result > 0 ? ResultDTO.ok("添加成功") : ResultDTO.fail("添加失败");
    }
}
