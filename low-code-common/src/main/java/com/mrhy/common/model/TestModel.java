package com.mrhy.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cooper
 * @description
 * @date 2022/2/15 5:59 PM
 */

public class TestModel {

    private Boolean isDelete;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        TestModel testModel=new TestModel();
        testModel.setDelete(Boolean.FALSE);
        String s = JSON.toJSONString(testModel);
        System.out.println(s);


        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);


    }

}
