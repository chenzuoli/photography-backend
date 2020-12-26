package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        List<Photography> photographies = photographyService.get_photographies();
        return ResultDTO.ok(photographies);
    }
}
