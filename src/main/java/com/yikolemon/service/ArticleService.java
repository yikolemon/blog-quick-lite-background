package com.yikolemon.service;

import com.yikolemon.entity.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 添加文章
     * @param article
     * @return
     */
    void save(Article article);

    void saveAll(List<Article> articleList);

    void deleteById(String id);

    void deleteList(List<String> ids);

    int update(Article article);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Article getById(String id);

    /*
    * 查询列表
    */
    List<Article> getList();



}
