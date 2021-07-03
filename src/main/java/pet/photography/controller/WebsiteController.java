package pet.photography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pet.photography.entity.Photography;
import pet.photography.service.PhotographyService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user chenzuoli on 2021/4/18 22:02
 * description: 网站页面接口
 */
@Controller
public class WebsiteController {
    @Autowired
    PhotographyService photographyService;

    public String nextDoorApp() {
        return "next_door_app";
    }

    @RequestMapping(value = "moment", method = RequestMethod.GET)
    public ModelAndView nextDoorMomment(@RequestParam("id") int id) {
        Photography photography = photographyService.getPhotographyById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("miniprogram/next_door_moment");
        modelAndView.addObject("photography", 12345);
        return modelAndView;
    }

    // return的string前面不能加/
    // GetMapping相当于RequestMapping Get请求
    @GetMapping("get_moments")
    String test(@RequestParam("id") int id, HttpServletRequest request) {
        //逻辑处理
        Photography photography = photographyService.getPhotographyById(id);
        request.setAttribute("photography", photography);
        return "moments";
    }


}
