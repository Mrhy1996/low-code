package com.mrhy.server.service.impl;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
public class UserInfoServiceImplTest {
    @Test
    public void testMobile() {
        String test = "[[1]](([3][0-9])|([4][0,1,4-9])|([5][0-3,5-9])|([6][2,5,6,7])|([7][0-8])|([8][0-9])|([9][0-3,5-9]))[0-9]{8}";
        Pattern compile = Pattern.compile(test);
        Matcher matcher = compile.matcher("cardNo=6200000000000000066,mobile=1826541960122,aaa");
        while (matcher.find()) {
            System.out.println("first\t" + matcher.group(0));
        }


    }

    @Test
    public void testBankNo() {
        String message = "2021-12-16 00:04:45:164|magpie-server-executor-magpieRpcQuery-40314|INFO ||ACP021121600044443570037446900|gray:false # [Reason for not match discounts] with [request info : cardNo=6230580000078644775,userId=c00194289621,mobile=18569053400,transChnl=0001,mchntCd=001980099990002,orderAt=1000,transDt=2021-12-16 00:04:45,icTmn=null,posTmn=001980099990002:null,chnlMchntCd=048000094980001,mchntDiscountCd=001980099990002:622156,skus=null] and [activity info : discountId=2102021102916283,activityNm=上海银行信用卡美好生活APP支付立减活动,factor=appoint_week,ruleTp=Logic]";
        String test = "cardNo=(.+?),";
        Pattern compile = Pattern.compile(test);
        Matcher matcher = compile.matcher(message);
        if (matcher.matches()) {
            System.out.println("first\t" + matcher.group(0));
        }
        String test2 = "card_No=(.+?),|cardNo\":\"(.+?)\"|cardNo=(.+?),";
        Pattern compile2 = Pattern.compile(test2);
        Matcher matcher2 = compile2.matcher(message);
        if (matcher2.matches()){
            System.out.println("second\t" + matcher2.group(0));
        }

        String test3 = "cardNo\":\"(.+?)\"";
        Pattern compile3 = Pattern.compile(test3);
        Matcher matcher3 = compile3.matcher(message);
        while (matcher3.find()) {
            System.out.println("third\t" + matcher3.group(1));
        }


    }

}