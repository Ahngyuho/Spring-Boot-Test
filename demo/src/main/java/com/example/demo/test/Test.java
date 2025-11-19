package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//
@Slf4j
@RestController
public class Test {
    //RequestMapping -> 매핑 전략 등록
    // 매핑 전략? url 을 보고 어떤 컨트롤러(핸들러)를 골라야 하는지 알려주는 역할
    //
    // 객체는 스프링 빈으로 등록해야만 함
    // 단순 url 만으로? url + http 메서드로 핸들러 구분?

//    @GetMapping("/test") // @GetMapping 에 있는 문자열을 통해 RequestMappingHandlerMapping 에 등록
//    public String test(@RequestParam String name) {
//        return "test";
//    }

    @PostMapping(value = "/test")
    public String test1(@RequestBody String name) {
        return "test";
    }

    @GetMapping("/test/{id}") // @GetMapping 에 있는 문자열을 통해 RequestMappingHandlerMapping 에 등록
    public String test1(@PathVariable Long id) {
        log.info("test id is {}", id);
        return "test";
    }



}
