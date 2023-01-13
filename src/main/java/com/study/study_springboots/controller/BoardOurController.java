package com.study.study_springboots.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/edit", method = RequestMethod.POST)   // /board_our/edit 위에꺼랑 합해쳐서 이런식으로 돼
    public ModelAndView edit(ModelAndView modelAndView) { //spring은 이전에 인스턴스화 되어있다 따라서 매개로 넣으면 바로 사용가능
        modelAndView.setViewName("board_our/edit");
        return modelAndView;
    }
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)  
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("firstString", "firstValue");
        DataInfors dataInfors = new DataInfors();
        ArrayList<BoardBean> boardList = dataInfors.getDataListWithBoardBean();
        modelAndView.addObject("boardList", boardList);
        
        modelAndView.setViewName("board_our/list");
        return modelAndView;    // --> Dispatcher Servlet
    }
    @RequestMapping(value = "/view", method = RequestMethod.GET)  
    public ModelAndView view(@RequestParam String uid, ModelAndView modelAndView) { //spring은 이전에 인스턴스화 되어있다 따라서 매개로 넣으면 바로 사용가능
        System.out.println(uid);
        DataInfors dataInfors = new DataInfors();
        BoardBean boardBean = dataInfors.getDataWithMamberBean(uid);
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
    public ModelAndView save(ModelAndView modelAndView) {
        // insert biz
        modelAndView.setViewName("board_our/list");
        return modelAndView;
    }
}