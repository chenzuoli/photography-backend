package pet.photography.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user chenzuoli on 2021/4/18 22:02
 * description: 网站页面接口
 */
public class WebsiteController {
    public String nextDoorApp() {
        return "next_door_app";
    }
}
