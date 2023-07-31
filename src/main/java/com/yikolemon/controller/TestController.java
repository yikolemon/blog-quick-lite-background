package com.yikolemon.controller;

import com.yikolemon.dao.cnblogs.MetaWbelogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yikolemon
 * @date 2023/7/31 22:31
 * @description
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MetaWbelogClient metaWbelogClient;

    @GetMapping("/getPost")
    public String getPost(){
        String post = metaWbelogClient.getPost("17096028");
        return post;
    }

}
