package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.PhotographyRepository;
import pet.photography.entity.Photography;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:30
 * description: 摄影作品服务类
 */
@Service
public class PhotographyService {
    @Autowired
    PhotographyRepository photographyRepository;

    public List<Photography> getPhotographies() {
        return photographyRepository.getPhotogrphies();
    }

    public int addPhotography(String open_id, String url, String type, String subject, String nick_name) {
        return photographyRepository.addPhotography(open_id, url, type, subject, nick_name);
    }
}
