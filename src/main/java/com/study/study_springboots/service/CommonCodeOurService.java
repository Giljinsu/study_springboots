package com.study.study_springboots.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.study_springboots.dao.CommonCodeOurDao;
import com.study.study_springboots.utils.Paginations;

@Service
public class CommonCodeOurService {
    @Autowired
    CommonCodeOurDao commonCodeOurDao;
    
    @Autowired
    AttachFileService attachFileService;

    public Object getOneWithAttachFiles(Object dataMap) {
        //Attach files ArrayList
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("attachFiles", attachFileService.getList(dataMap));
        result.putAll((Map<String,Object>)this.getOne(dataMap));
        // putAll은 Map에 Map을 넣을때 유용 같은키이면 덮어써짐
        // put으로 하면 꺼낼때 get get 이런식으로 꺼내야해서 복잡

        return result;
    }

    public Object insertWithFilesAndGetList(Object dataMap) {
        //insert files
        Object result =attachFileService.insertMulti(dataMap);
        result = this.insertOne(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object insertAndGetList(Object dataMap) {
        Object result = this.insertOne(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object updateAndGetList(Object dataMap) {
        Object result = this.updateOne(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object deleteandGetList(Object dataMap) {
        Object result = this.deleteOne(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    
    public Object getListWithPagination(Object dataMap){
        Map<String, Object> result = new HashMap<String,Object>();
        int totalCount = (int)this.getTotal(dataMap);
        int currentPage = (int) ((Map<String, Object>) dataMap).get("currentPage");
        Paginations paginations = new Paginations(totalCount, currentPage);
        result.put("paginations", paginations);
        ((Map<String, Object>) dataMap).put("pageBegin",paginations.getPageBegin());
        result.put("resultList", this.getList(dataMap));
        return result;
    }

    public Object getTotal(Object dataMap) {
        String sqlMapId= "CommonCodeOur.selectTotal";

        Object result = commonCodeOurDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object getList(Object dataMap){
        String sqlMapId = "CommonCodeOur.selectListByUID";
        Object result = commonCodeOurDao.getList(sqlMapId, dataMap);
        return result;
    }

    public Object getOne(Object dataMap) {
        String sqlMapId= "CommonCodeOur.selectByUID";

        Object result = commonCodeOurDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object updateOne(Object dataMap) {
        String sqlMapId= "CommonCodeOur.updateByUID";

        Object result = commonCodeOurDao.updateOne(sqlMapId, dataMap);
        return result;
    }

    public Object deleteOne(Object dataMap) {
        String sqlMapId= "CommonCodeOur.deleteByUID";

        Object result = commonCodeOurDao.deleteeOne(sqlMapId, dataMap);
        return result;
    }

    public Object deleteMulti(Object dataMap) {
        String sqlMapId= "CommonCodeOur.deleteMultiByUIDs";

        Object result = commonCodeOurDao.deleteeOne(sqlMapId, dataMap);
        return result;
    }

    public Object insertOne(Object dataMap) {
        String sqlMapId= "CommonCodeOur.insertWithUID";

        Object result = commonCodeOurDao.insertOne(sqlMapId, dataMap);
        return result;
    }
}
 