package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j      //로거 사용 가능
public class HomeController {

    //URL + "/" 링크에 대응
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";  //home.html을 띄움
    }
}
