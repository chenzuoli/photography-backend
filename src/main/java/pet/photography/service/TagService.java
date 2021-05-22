package pet.photography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.photography.dao.TagRepository;
import pet.photography.entity.Tag;

import java.util.List;

/**
 * Created by user chenzuoli on 2021/5/22 20:32
 * description: 标签服务类
 */
@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public List<Tag> getTags() {
        return tagRepository.getTags();
    }
}
