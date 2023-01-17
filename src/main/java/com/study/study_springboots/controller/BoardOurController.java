package com.study.study_springboots.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.study_springboots.beans.BoardBean;
import com.study.study_springboots.service.DataInfors;

// * Cast 
// - use bootstrap
// - 항목 : title, content,userName, date
// - CRUD : 
//   + list.jsp(/board) -> view.jsp(/board_our/view) -> list.jsp(/board_our/list)
//   + list.jsp(/board) -> form.jsp(/board_our/form) -> list.jsp(/board_our/save) with Post
//   + view.jsp(/board_our/view) -> edit.jsp(/board_our/edit) -> list.jsp(/board_our/save)
@Controller
@RequestMapping(value = "/board_our")
public class BoardOurController {
    
    @Autowired //Bean에 올려진거를 사용하는거다.
    DataInfors dataInfors;// 이것이 DI new를 안붙이는이유: Bean에 올려놔서 
    @RequestMapping(value = "/edit", method = RequestMethod.POST)   // /board_our/edit 위에꺼랑 합해쳐서 이런식으로 돼
    public ModelAndView edit(ModelAndView modelAndView) { //spring은 이전에 인스턴스화 되어있다 따라서 매개로 넣으면 바로 사용가능
        modelAndView.setViewName("board_our/edit");
        return modelAndView;
    }
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)  
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("firstString", "firstValue");
        // DataInfors dataInfors = new DataInfors();
        ArrayList<BoardBean> boardList = dataInfors.getDataListWithBoardBean();
        modelAndView.addObject("boardList", boardList);
        
        modelAndView.setViewName("board_our/list");
        return modelAndView;    // --> Dispatcher Servlet
    }
    // @RequestMapping(value = "/view", method = RequestMethod.GET)  
    @RequestMapping(value = "/view/{action_uid}", method = RequestMethod.GET)  //{}안은 변수다 표시
    public ModelAndView view(@PathVariable String action_uid, ModelAndView modelAndView) { //spring은 이전에 인스턴스화 되어있다 따라서 매개로 넣으면 바로 사용가능
        // action_uid는 servlet에서 넘어오는 변수야 라는 것을 표현해야함
        System.out.println(action_uid);
        // DataInfors dataInfors = new DataInfors();
        BoardBean boardBean = dataInfors.getDataWithMamberBean(action_uid);
        modelAndView.addObject("boardBean", boardBean);

        modelAndView.setViewName("board_our/view");

        return modelAndView;
    }
    @RequestMapping(value = "/form", method = RequestMethod.GET)    
    public ModelAndView form(ModelAndView modelAndView) {
        modelAndView.setViewName("board_our/form");
        return modelAndView;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    // public ModelAndView save(@RequestParam Hashmap<String,String>, ModelAndView modelAndView) {    
    public ModelAndView save(BoardBean boardBean, ModelAndView modelAndView) {
        //BoardBean 이라는것은 spring에 없으니까 찾아본다? 이래서 작동함
        //주의점 BoardBean의 변수 이름과 form.jsp에서의 이름이 같아야함
        // insert biz
        modelAndView.setViewName("board_our/list");
        return modelAndView;
    }
}