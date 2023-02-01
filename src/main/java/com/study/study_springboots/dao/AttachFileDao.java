package com.study.study_springboots.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// @Repository
@Component
public class AttachFileDao {
    @Autowired
    private SqlSessionTemplate sessionTemplate;
    public Object getList(String sqlMapId, Object dataMap) {

        Object result = sessionTemplate.selectList(sqlMapId, dataMap);
        return result;
    }

    public Object getOne(String sqlMapId, Object dataMap) {

        Object result = sessionTemplate.selectOne(sqlMapId, dataMap);
        return result;
    }

    public Object updateOne(String sqlMapId, Object dataMap) {
        
        Object result = sessionTemplate.update(sqlMapId, dataMap);
        //update() return값은 업데이트 수 int형이다 0이면 안된거다
        return result;
    }

    public Object deleteeOne(String sqlMapId, Object dataMap) {
        
        Object result = sessionTemplate.delete(sqlMapId, dataMap);
        //delete() return값은 삭제한 갯수 int형이다 0이면 안된거다
        return result;
    }

    public Object insertOne(String sqlMapId, Object dataMap) {
        
        Object result = sessionTemplate.insert(sqlMapId, dataMap);
        //update() return값은 업데이트 수 int형이다 0이면 안된거다
        return result;
    }
    
    
}
