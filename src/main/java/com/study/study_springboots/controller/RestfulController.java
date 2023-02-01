package com.study.study_springboots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.study_springboots.service.CommonCodeOurService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin // 다른기종 연결?
public class RestfulController {
    @RequestMapping(value = "/api/v1/helloworld", method = RequestMethod.GET)
   public String helloWorld() {

    return "hello world!";
   } 
   
   // params - title : "learn ajax", description : "we learn with ajax"
   @RequestMapping(value = "/api/v1/requestparams", method = RequestMethod.GET)
   public Map requestparams(@RequestParam Map<String, Object> params) {
    Map<String,Object> result = new HashMap<String,Object>();
    result.putAll(params);
    result.put("id", "yojulab");
    return result;
   } 

   @Autowired
   CommonCodeOurService commonCodeOurService;
   // currentPage : 1
   @RequestMapping(value = "/api/v1/requestparamsWithDB", method = RequestMethod.POST)
   public Map requestparamsWithDB(@RequestParam Map<String, Object> params) {
    params.put("currentPage", Integer.parseInt((String) params.get("currentPage")));
    params.put("pageScale", 10);

    Map<String, Object> resultMap = new HashMap<>();
    resultMap = (Map<String, Object>) commonCodeOurService.getListWithPagination(params);
    
    return resultMap;
   } 
   
}  
