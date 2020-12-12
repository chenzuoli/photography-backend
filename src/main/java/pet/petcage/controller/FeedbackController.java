package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.service.FeedbackSerevice;


/**
 * Created by user chenzuoli on 2020/3/25 11:19
 * description: 反馈信息控制器
 */
@RestController
public class FeedbackController {

    @Autowired
    FeedbackSerevice feedbackSerevice;

    /**
     * 添加信息反馈
     *
     * @param open_id          用户open_id
     * @param feedback_type    反馈类型：订单反馈
     * @param feedback_content 反馈内容
     * @param satisfy_grade    客户满意度
     * @param pictures         反馈图片
     * @param latitude         反馈时用户径度
     * @param longitude        反馈时用户纬度
     * @param petcage_id       笼牌号
     * @param description      反馈描述
     * @return 返回数值  > 0代表添加成功
     */
    @RequestMapping(value = "/add_feedback", method = RequestMethod.POST)
    ResultDTO addFeedback(String order_id,
                          @RequestParam("open_id") String open_id,
                          @RequestParam("feedback_type") String feedback_type,
                          @RequestParam("feedback_content") String feedback_content,
                          @RequestParam("satisfy_grade") String satisfy_grade,
                          @RequestParam("pictures") String pictures,
                          @RequestParam("latitude") String latitude,
                          @RequestParam("longitude") String longitude,
                          @RequestParam("petcage_id") String petcage_id,
                          @RequestParam("description") String description) {
        int result = feedbackSerevice.addFeedback(order_id, open_id, feedback_type, feedback_content, satisfy_grade, pictures,
                latitude, longitude, petcage_id, description);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加反馈失败");
        }
    }

}
