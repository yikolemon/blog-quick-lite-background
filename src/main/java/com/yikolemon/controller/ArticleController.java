package com.yikolemon.controller;

import com.yikolemon.entity.Article;
import com.yikolemon.entity.Page;
import com.yikolemon.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yikolemon.service.ArticleService;

import javax.validation.constraints.NotNull;
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

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public AjaxResult getArticlePageWithOutContent(@NotNull Integer pageNum) {
        Page<Article> content = articleService.getArticlePageWithOutContent(pageNum);
        return AjaxResult.addSuccess(content);
    }

    @GetMapping("/content")
    @ApiOperation("文章内容查询")
    public AjaxResult getArticleContent(@NotNull String articleId) {
        Article article = articleService.getArticleContent(articleId);
        return AjaxResult.addSuccess(article);
    }

    @GetMapping("/category")
    @ApiOperation("分类文章查询")
    public AjaxResult getArticlePageWithOutContentByCategory(@NotNull Integer pageNum,@NotNull String category){


    }

}

