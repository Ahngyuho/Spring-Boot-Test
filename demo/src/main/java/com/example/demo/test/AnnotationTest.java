package com.example.demo.test;

public class AnnotationTest {

    @SimpleMapping("/home")
    public String home() {
        return "home";
    }

    @SimpleMapping("/home1")
    public String home1() {
        return "home";
    }

    @SimpleMapping("/home2")
    public String home2() {
        return "home";
    }
}
