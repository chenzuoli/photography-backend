package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.FeedbackRepository;
import pet.petcage.entity.Feedback;


/**
 * Created by user chenzuoli on 2020/3/25 11:18
 * description: 反馈信息服务
 */
@Service
public class FeedbackSerevice extends BaseService<Feedback> {
    @Autowired
    FeedbackRepository feedRepo;

    @Override
    public Feedback getById(String id) {
        return null;
    }

    /**
     * 添加反馈
     *
     * @param order_id         订单id
     * @param open_id          用户open_id
     * @param feedback_type    反馈类型：1订单反馈 2设备维修反馈 3系统反馈
     * @param feedback_content 反馈内容
     * @param pictures         反馈图片
     * @param latitude         反馈时用户径度
     * @param longitude        反馈时用户纬度
     * @param petcage_id       反馈设备id
     * @param description      反馈描述
     * @return 插入成功与否
     */
    public int addFeedback(String order_id, String open_id, String feedback_type, String feedback_content, String satisfy_grade,
                           String pictures, String latitude, String longitude, String petcage_id, String description) {
        System.out.println("add feedback params: " + open_id + "," + feedback_content + "," + feedback_content + "," + satisfy_grade + "," + pictures + "," + latitude + "," + longitude + "," + petcage_id + "," + description);
        return feedRepo.addFeedback(order_id, open_id, feedback_type, feedback_content, satisfy_grade, pictures,
                latitude, longitude, petcage_id, description);
    }

}
