package com.yikolemon.service.impl;

import com.yikolemon.dao.ArticleRepository;
import com.yikolemon.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yikolemon.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yikolemon
 * @date 2023/7/22 21:58
 * @description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public int create(Article article) {
        Article save = articleRepository.save(article);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<Article> deleteList = new ArrayList<>();

        for(String id:ids){
            Article article = new Article();
            article.setId(id);
            deleteList.add(article);
        }
        articleRepository.deleteAll(deleteList);

        return ids.size();
    }


    //该方法还不确定
    @Override
    public int update(Article article) {
        articleRepository.insert(article);
        return 1;
    }

    @Override
    public Article getById(String id) {
        return articleRepository.findByid(id);
    }

    @Override
    public List<Article> getList() {
        return  articleRepository.findAll();
    }
}
