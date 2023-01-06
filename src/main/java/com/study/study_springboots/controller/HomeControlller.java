package com.study.study_springboots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControlller {
    @RequestMapping(value = {"","/", "/main"})
    public String main() {
        int i=0;
        return "WEB-INF/views/main.jsp";
    }
    @RequestMapping(value = "/home")
    public void home() {
        int i=0;
    }
    @RequestMapping(value = "/homejsp")
    public String homejsp() {
        int i=0;
        return "WEB-INF/views/home.jsp"; //이 리턴은 spring에게 전달 IOC spring 다한다.
        // return에 String을 넣으면 경로로 인식
    }
    @RequestMapping(value = "/homehtml")
    public String homehtml() {
        int i=0;
        return "WEB-INF/views/home.html"; //이 리턴은 spring에게 전달 IOC spring 다한다.
        // return에 String을 넣으면 경로로 인식
    }
    
}
