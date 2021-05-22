package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Tag;
import pet.photography.service.TagService;

import java.util.List;

/**
 * Created by user chenzuoli on 2021/5/22 20:33
 * description: 标签控制类
 */
@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(value = "/get_tags", method = RequestMethod.GET)
    public ResultDTO getTags() {
        List<Tag> tags = tagService.getTags();
        return tags.size() > 0 ? ResultDTO.ok(tags) : ResultDTO.fail("标签为空");
    }
}
