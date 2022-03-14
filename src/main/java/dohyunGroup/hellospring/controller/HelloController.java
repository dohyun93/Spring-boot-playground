package dohyunGroup.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // 기본적으로 resources밑에 hello.html을 찾아 렌더링 한다.
        // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다.
        // 스프링 부트 템플릿엔진 기본 viewName 매핑.
        // `resources:templates/` + {ViewName} + `.html`

        // spring-boot-devtools 라이브러리 추가하면 html파일을 컴파일만 해주면 서버 재시작 없이 view 파일 변경 가능하다.
    }
}
