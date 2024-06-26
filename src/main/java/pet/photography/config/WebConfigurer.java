package pet.photography.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pet.photography.interceptor.LoginInterceptor;

/**
 * Created by user chenzuoli on 2020/4/21 23:33
 * description: 拦截器配置
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/wx_login", "/tt_login", "/login_app", "/register", "/register_app",
                        "/register_app_user", "/sms_code", "/open_id", "/tt_open_id", "/get_competitions",
                        "/get_votes", "/get_photographies", "/get_comments", "/app", "/get_vote_by_id",
                        "/get_photography_by_id", "/get_user_by_open_id", "/moment", "/miniprogram/next_door_moment.html",
                        "/get_moments", "/moments.html", "/competition_work", "/competition_work.html");
//        super.addInterceptors(registry);    //较新Spring Boot的版本中这里可以直接去掉，否则会报错
    }
}