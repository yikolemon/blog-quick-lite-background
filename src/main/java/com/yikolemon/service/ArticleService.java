package com.yikolemon.service;

import com.yikolemon.entity.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 添加文章
     * @param article
     * @return
     */
    int create(Article article);

    /**
     * 删除文章
     */

    int delete(List<String> ids);

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
