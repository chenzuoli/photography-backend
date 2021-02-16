package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.FeedbackRepository;

/**
 * Created by user chenzuoli on 2021/2/16 08:50
 * description: 反馈信息服务类
 */
@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    public int addFeedback(String open_id, String content) {
        return feedbackRepository.addFeedback(open_id, content);
    }
}
