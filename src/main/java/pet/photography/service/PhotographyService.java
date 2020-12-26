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

    public List<Photography> get_photographies() {
        return photographyRepository.get_photogrphies();
    }
}
