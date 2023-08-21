package com.yikolemon.service.impl;

import com.yikolemon.dao.template.ArticleDao;
import com.yikolemon.dao.ArticleRepository;
import com.yikolemon.entity.Article;
import com.yikolemon.entity.Page;
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

    @Autowired
    private ArticleDao articleDao;


    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void saveAll(List<Article> articleList) {
        articleRepository.saveAll(articleList);
    }

    @Override
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteList(List<String> ids) {
        List<Article> articleList=new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            articleList.get(i).setId(ids.get(i));
        }
        articleRepository.deleteAll(articleList);
    }


    //该方法还不确定
    @Override
    public int update(Article article) {
        //articleRepository.insert(article);
        return 1;
    }

    @Override
    public List<Article> getAllList() {
        return  articleRepository.findAll();
    }

    @Override
    public Page<Article> getArticlePageWithOutContent(Integer pageNum) {
        return articleDao.getArticlePageWithOutContent(pageNum);
    }

    @Override
    public Article getArticleContent(String articleId) {
        return articleRepository.findByid(articleId);
    }


}
