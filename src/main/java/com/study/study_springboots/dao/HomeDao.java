package com.study.study_springboots.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class HomeDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate; //mybatis 사용하기위한 준비

    public Object getList() {
        String statement = "Home.selectFromANSWERSByQUESTIONS_UID";
        Map parameter = new HashMap();
        parameter.put("QUESTION_UID", "Q2");
        Object resultset = sqlSessionTemplate.selectList(statement, parameter);
        return resultset;
    }
}