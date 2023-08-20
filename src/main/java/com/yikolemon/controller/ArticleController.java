package com.yikolemon.controller;

import com.yikolemon.entity.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yikolemon.service.ArticleService;

import java.util.List;

/**
 * Web层，实现操作MongoDB
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章操作")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    @ApiOperation("创建文章")
    public String create(@RequestBody Article article) {
        try{
            articleService.save(article);
            return "文章创建成功";
        }
        catch (Exception e){
            return "文章创建失败";
        }
    }

    @PostMapping("/delete")
    @ApiOperation("批量删除文章")
    public String delete(@RequestParam("ids") List<String> ids) {

        try {
            articleService.deleteList(ids);
            return "删除文章成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除文章失败";
        }
    }

    @PostMapping("/get")
    @ApiOperation("查询")
    public Article getById(String id) {
        Article article = articleService.getById(id);
        return article;
    }

}

