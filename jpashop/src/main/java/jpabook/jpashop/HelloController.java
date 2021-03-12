package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")    //URL의 /hello에 대응된다.
    public String hello(Model model){
        //Controller는 Model에게 데이터를 싣는다.
        //Model에서 View로 데이터를 넘긴다.
        //View는 데이터를 출력
        model.addAttribute("data", "hello!");
        return "hello"; //리턴값 : html 파일 이름
    }
}
