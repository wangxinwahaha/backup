/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.controller;

import com.unisinsight.framework.uuv.base.ListParams;
import com.unisinsight.framework.uuv.model.UserInfoDO;
import com.unisinsight.framework.uuv.service.TestUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分页list-Controller层
 *
 * @author liuran [KF.liuranA@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@RestController
@RequestMapping("/v0.1/test")
public class TestListParamController {

    @Autowired
    private TestUserInfoService userInfoService;

    @GetMapping
    public Object test(ListParams<UserInfoDO> list){
        return userInfoService.list(list);
    }

    @GetMapping(value = "ttt")
    public void test(){
        System.out.println();
    }
}
