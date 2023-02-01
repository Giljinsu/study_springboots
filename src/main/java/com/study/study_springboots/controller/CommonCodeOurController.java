package com.study.study_springboots.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.study.study_springboots.service.CommonCodeOurService;
import com.study.study_springboots.utils.CommonUtils;

@Controller
@RequestMapping(value = "/commonCodeOur")
public class CommonCodeOurController {
    
    @Autowired
    CommonCodeOurService commonCodeOurService;

    @Autowired
    CommonUtils commonUtils;

    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST)
    public ModelAndView insert(MultipartHttpServletRequest multipartHttpServletRequest,
                    @RequestParam Map<String, Object> params, 
                    ModelAndView modelAndView) throws IOException {

        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file_first");
        String fileName = multipartFile.getOriginalFilename();

        String relativePath = "src\\main\\resources\\static\\files\\";
        // file 저장
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(relativePath+fileName));
        // 파일 만들기 paths.get은 합치기 위함
        bufferedWriter.write(new String(multipartFile.getBytes()));
        bufferedWriter.flush();
        

        Object resultMap = commonCodeOurService.insertAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("/commonCode_our/list");
        return modelAndView;
    }
    //post방식으로만 되어야함 get방식은 주소창에 표시하는데 주소창이 255바이트까지 표시 따라서 파일은 불가
    @RequestMapping(value = {"/insertMulti"}, method = RequestMethod.POST)
    public ModelAndView insertMulti(MultipartHttpServletRequest multipartHttpServletRequest,
                    @RequestParam Map<String, Object> params, 
                    ModelAndView modelAndView) throws IOException {

        Iterator<String> fileNames =  multipartHttpServletRequest.getFileNames();
        // String relativePath = "src/main/resources/static/files/";
        String absolutePath = commonUtils.getRelativeToAbsolutePath("src/main/resources/static/files/");
        //Iterator로 반환 
        // 리눅스 방식 경로 / 그리고 앞에 /넣어야 되는데 안넣으면 상대 경로
        

        Map attachfile = null;
        List attachfiles = new ArrayList<>();
        String physicalFileName = commonUtils.getUniqueSequence();
        // String storePath = relativePath + physicalFileName + "\\" ;
        String storePath = absolutePath + physicalFileName + File.separator; //separator가 맞춰줌
        File newPath = new File(storePath);
        newPath.mkdir(); // 폴더를 미리 만들기 
        while(fileNames.hasNext()) { // 값이 있냐?
            String fileName =fileNames.next();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(fileName);
            String originalFileName = multipartFile.getOriginalFilename();

            if(originalFileName != null && multipartFile.getSize() != 0) {

                String storePathFileName = storePath+originalFileName;
                multipartFile.transferTo(new File(storePathFileName));
                
                attachfile = new HashMap<>();
                attachfile.put("ATTACHFILE_SEQ", commonUtils.getUniqueSequence());
                attachfile.put("SOURCE_UNIQUE_SEQ", params.get("COMMON_CODE_ID"));
                attachfile.put("ORGINALFILE_NAME", originalFileName);
                attachfile.put("PHYSICALFILE_NAME", physicalFileName);
                attachfile.put("REGISTER_SEQ", params.get("REGISTER_SEQ"));
                attachfile.put("MODIFIER_SEQ", params.get("MODIFIER_SEQ"));
                
                attachfiles.add(attachfile);
            }
            // SOURCE_UNIQUE_SEQ, ORIGINALFILE_NAME, PHUSICALFILE_NAME
        }
        params.put("attachfiles", attachfiles);

        Object resultMap = commonCodeOurService.insertWithFilesAndGetList(params);
        modelAndView.addObject("resultMap",resultMap);
        modelAndView.setViewName("/commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/form"}, method = RequestMethod.GET)
    public ModelAndView form(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        modelAndView.setViewName("/commonCode_our/edit");
        return modelAndView;
    }

    @RequestMapping(value = {"/formMulti"}, method = RequestMethod.GET)
    public ModelAndView formMulti(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        modelAndView.setViewName("/commonCode_our/editMulti");
        return modelAndView;
    }

    @RequestMapping(value = {"/delete/{uniqueId}"}, method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam Map<String, Object> params, @PathVariable String uniqueId,
        ModelAndView modelAndView) {
            params.put("COMMON_CODE_ID", uniqueId);
            Object resultMap = commonCodeOurService.deleteandGetList(params);
            modelAndView.addObject("resultMap", resultMap);
            modelAndView.setViewName("/commonCode_our/list");
            return modelAndView;
    }

    @RequestMapping(value = {"/deleteMulti"}, method = RequestMethod.POST)
    public ModelAndView deletMulti(HttpServletRequest httpServletRequest, @RequestParam Map<String, Object> params, 
                ModelAndView modelAndView) {
            // modelAndView.addObject("resultMap", resultMap);
            String[] deleteMultis = httpServletRequest.getParameterMap().get("COMMON_CODE_ID");
            params.put("deleteMultis",deleteMultis);
            // Object resultMap = commonCodeOurService.deleteMulti(params);
            
            modelAndView.setViewName("/commonCode_our/list");
            return modelAndView;
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public ModelAndView update(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = commonCodeOurService.updateAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("/commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/listPagination/{currentPage}"}, method = RequestMethod.GET)
    public ModelAndView listPagenation(@RequestParam Map<String, Object> params, 
            @PathVariable String currentPage, ModelAndView modelAndView) {
        params.put("currentPage", Integer.parseInt(currentPage));
        params.put("pageScale", 10);
        Object resultMap = commonCodeOurService.getListWithPagination(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("/commonCode_our/list_pagination");
        return modelAndView;
    }
    
    @RequestMapping(value = {"/list","/",""}, method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = commonCodeOurService.getList(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("/commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit/{uniqueId}"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam Map<String, Object> params, @PathVariable String uniqueId,
        ModelAndView modelAndView) {
            params.put("COMMON_CODE_ID", uniqueId);
            Object resultMap = commonCodeOurService.getOne(params);
            modelAndView.addObject("resultMap", resultMap);
            modelAndView.setViewName("/commonCode_our/edit");
            return modelAndView;
    }

    @RequestMapping(value = {"/editMulti/{uniqueId}"}, method = RequestMethod.GET)
    public ModelAndView editMulti(@RequestParam Map<String, Object> params, @PathVariable String uniqueId,
        ModelAndView modelAndView) {
            params.put("SOURCE_UNIQUE_SEQ", uniqueId);
            params.put("COMMON_CODE_ID", uniqueId);
            Object resultMap = commonCodeOurService.getOneWithAttachFiles(params);
            modelAndView.addObject("resultMap", resultMap);
            modelAndView.setViewName("/commonCode_our/editMulti");
            return modelAndView;
    }

    @RequestMapping(value = {"/updateMulti"}, method = RequestMethod.POST)
    public ModelAndView updateMulti(MultipartHttpServletRequest multipartHttpServletRequest,
                    @RequestParam Map<String, Object> params, 
                    ModelAndView modelAndView) throws IOException {                

        Iterator<String> fileNames =  multipartHttpServletRequest.getFileNames();
        while(fileNames.hasNext()) {
            String value = (String)params.get(fileNames.next());
            System.out.println(value);
            if(value!=null) {
                // update코드
            }
        }

        modelAndView.setViewName("commonCode_our/list");
        return modelAndView;
    }


}
