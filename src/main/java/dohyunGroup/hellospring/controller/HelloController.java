package dohyunGroup.hellospring.controller;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
        // viewResolver가 템플릿엔진에 hello-template.html을 처리하도록 요청.
        // attribute를 추가해주는 것이 있기 때문에 static의 hello-template이 아니라 템플릿에서 찾아서 view해준다.
    }

    // API 방식 1 -> @ResponseBody의 첫 등장!!
    // 단 응답이 json타입이 아니다.
    @GetMapping("hello-string")
    @ResponseBody
    // name이라는 path param을 받아서 name1에 넣어준다.
    public String helloString(@RequestParam("name") String name1){
        return "hello" + name1;
    }

    // API 방식 2 -> @ResponseBody 로 json타입의 응답을 한다.!
    // http://localhost:8080/hello-api?name=권도현
    // {"name":"권도현"}
    // 기본 문자처리: StringHttpMessageConverter 가 처리
    // 여기처럼 기본 객체처리: MappingJackson2HttpMessageConverter 가 처리 (json화)
    // 실무에서 jackson이라는 라이브러리와 gson 라이브러리가 json으로 변환해주는 기능을 한다.
    // jackson이라는 라이브러리를 스프링에서 기본으로 채택함.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 객체
        hello.setName(name);
        return hello;
    }

    // 자바 빈 표준 방식.
    static class Hello{
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}