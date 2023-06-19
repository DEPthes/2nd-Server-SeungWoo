package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러
public class HelloController {

    @GetMapping("hello") // '/hello'를 통해 접근 가능
    public String Hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // 반환할 'hello'라는 이름의 파일
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // API 방식 --> View Resolver 대신 Converter 사용
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

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
