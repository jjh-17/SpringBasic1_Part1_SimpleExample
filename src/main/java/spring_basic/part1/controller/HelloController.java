package spring_basic.part1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //웹에서 '/hello'로 들어올 시 아래 메서드가 실행된다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");

        //templates 내 hello.html로 이동
        return "hello";
    }

    /*
    [MVC와 Template]
    '/hello-mvc'로 이동 시 아래 메서드 실행
    @RequestParam(value = "name", required = True)
        외부에서 name을 입력받는다.
        required가 False일 경우, name을 입력받지 않더라도 default를 출력하나, True일 경우 name입력은 필수

    http://localhost:8080/hello-mvc?name=1234   ==> hello 1234
    http://localhost:8080/hello-mvc             ==> hello null

    페이지 소스보기를 누르면 템플릿이 보임
    */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);

        //templates 내 hello-template.html로 이동
        return "hello-template";
    }

    /*
    http://localhost:8080/hello-string?name=spring ==> hello spring
    http://localhost:8080/hello-string             ==> hello null

    페이지 소스보기를 눌러도 출력문 밖에 보이지 않음음
     */
    @GetMapping("hello-string")
    @ResponseBody //http의 body 부분에 반환값을 넣는다.
    public String helloString(@RequestParam(value = "name", required = false) String name) {
        return "hello " + name;
    }


    /*
    [API]
    JSON 방식으로 출력
    소스 보기 == 출력
    */
    @GetMapping("hello-api")
    @ResponseBody //http의 body 부분에 반환값을 넣는다.
    public Hello helloApi(@RequestParam(value="name", required = false) String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
