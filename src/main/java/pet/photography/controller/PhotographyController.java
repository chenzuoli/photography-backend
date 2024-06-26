package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.photography.dto.ResultDTO;
import pet.photography.entity.Photography;
import pet.photography.service.PhotographyService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/26 08:31
 * description: 摄影作品控制器
 */
@RestController
public class PhotographyController {
    @Autowired
    PhotographyService photographyService;

    @RequestMapping(value = "/get_photographies", method = RequestMethod.GET)
    public ResultDTO getPhotographies() {
        List<Photography> photographies = photographyService.getPhotographies();
        return ResultDTO.ok(photographies);
    }

    @RequestMapping(value = "/add_photography", method = RequestMethod.POST)
    public ResultDTO addPhotography(@RequestParam("open_id") String open_id,
                                    @RequestParam("url") String url,
                                    @RequestParam("type") String type,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("nick_name") String nick_name,
                                    @RequestParam("photographer") String photographer,
                                    @RequestParam("tag_id") String tag_id) {
        int result = photographyService.addPhotography(open_id, url, type, subject, nick_name, photographer, tag_id);
        return result > 0 ? ResultDTO.ok("添加成功") : ResultDTO.fail("添加失败");
    }

    @RequestMapping(value = "/get_photography_by_id", method = RequestMethod.GET)
    public ResultDTO getPhotographyById(@RequestParam("id") int id) {
        Photography photography = photographyService.getPhotographyById(id);
        return photography != null ? ResultDTO.ok(photography) : ResultDTO.fail("查询无结果");
    }
}
