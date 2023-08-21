package com.yikolemon.service;

import com.yikolemon.entity.Article;
import com.yikolemon.entity.Page;

import java.util.List;

public interface ArticleService {

    /**
     * 添加文章
     */
    void save(Article article);

    void saveAll(List<Article> articleList);

    void deleteById(String id);

    void deleteList(List<String> ids);

    int update(Article article);

    /*
    * 查询列表
    */
    List<Article> getAllList();

    Page<Article> getArticlePageWithOutContent(Integer pageNum);

    Article getArticleContent(String articleId);


}
