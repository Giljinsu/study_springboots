package com.study.study_springboots.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.study_springboots.beans.BoardBean;
import com.study.study_springboots.service.DataInfors;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
        @RequestMapping(value = {"/","/list"})
        public ModelAndView list() {
                DataInfors dataInfors = new DataInfors();
                ArrayList<BoardBean> dataInfo = dataInfors.getDataListWithBoardBean();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("dataInfo", dataInfo);
                modelAndView.setViewName("/notice/list");
                return modelAndView;
        }
        @RequestMapping(value = "/edit/{title}", method = RequestMethod.GET)
        public ModelAndView view(@PathVariable String title) {
                DataInfors dataInfors = new DataInfors();
                ModelAndView modelAndView = new ModelAndView();
                BoardBean boardBean = dataInfors.getDataWithMamberBean(title);
                modelAndView.addObject("boardBean", boardBean);
                
                modelAndView.setViewName("/notice/edit");
                return modelAndView;

        }
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public ModelAndView view(@RequestParam HashMap<String,String> hashMap) {
                DataInfors dataInfors = new DataInfors();
                BoardBean boardBean = dataInfors.addDataBean(hashMap);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("boardBean", boardBean);
                modelAndView.setViewName("/notice/view");
                

                return modelAndView;

        }
}
