package com.yikolemon.controller;

import com.yikolemon.dao.cnblogs.MetaWbelogClient;
import com.yikolemon.dao.cnblogs.Oauth2BlogClient;
import com.yikolemon.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private Oauth2BlogClient oauth2BlogClient;

    @GetMapping("/getPost")
    @ResponseBody
    public String getPost(){
        Article article = metaWbelogClient.getPost("17323618");
        return article.getContent();
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Article> getAll(){
        List<Article> allArticleList = oauth2BlogClient.getAllArticleList();
        return allArticleList;
    }


}
