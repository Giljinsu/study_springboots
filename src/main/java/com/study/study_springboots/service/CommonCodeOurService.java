package com.study.study_springboots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.study_springboots.dao.CommonCodeOurDao;

@Service
public class CommonCodeOurService {
    @Autowired
    CommonCodeOurDao commonCodeOurDao;

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
    public Object insertOne(Object dataMap) {
        String sqlMapId= "CommonCodeOur.insertWithUID";

        Object result = commonCodeOurDao.insertOne(sqlMapId, dataMap);
        return result;
    }
}
 