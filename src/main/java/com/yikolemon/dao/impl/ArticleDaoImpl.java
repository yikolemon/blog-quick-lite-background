package com.yikolemon.dao.impl;

import com.yikolemon.dao.template.ArticleDao;
import com.yikolemon.entity.Article;
import com.yikolemon.entity.Page;
import com.yikolemon.exception.PageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 18:15
 * @description
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Value("${blog-quick-lite-background.pageSize}")
    private Integer pageSize;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Article> getArticleListWithOutContent() {
        // 创建查询
        Query query = new Query();
        //Criteria criteria = new Criteria();
        // 设置条件：
        //criteria.and("_id").is(id);
        //将条件添加到查询内
        //query.addCriteria(criteria);
        query.fields().exclude("content");
        return mongoTemplate.find(query, Article.class);
    }


    @Override
    public Page<Article> getArticlePageWithOutContent(Integer pageNum) {
        // 分页参数
        Query query = new Query();
        //将条件添加到查询内
        query.fields().exclude("content");
        page(query,pageNum);
        int count =(int) mongoTemplate.count(query, Article.class);
        List<Article> articleList = mongoTemplate.find(query, Article.class);
        Integer totalPageNum = getTotalPageNum(count, pageSize);
        return new Page<Article>(pageNum,totalPageNum,articleList);
    }


    public List<Article> getArticleListByCategory(String category){
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 设置条件：
        criteria.and("category").is(category);
        //将条件添加到查询内
        query.addCriteria(criteria);
        query.fields().exclude("content");
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public Page<Article> getArticlePageWithOutContentByCategory(Integer pageNum, String category) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 设置条件：
        criteria.and("category").is(category);
        //将条件添加到查询内
        query.addCriteria(criteria);
        query.fields().exclude("content");
        page(query,pageNum);
        int count =(int) mongoTemplate.count(query, Article.class);
        List<Article> articleList = mongoTemplate.find(query, Article.class);
        Integer totalPageNum = getTotalPageNum(count, pageSize);
        return new Page<Article>(pageNum,totalPageNum,articleList);
    }

    @Override
    public List<Article> getArticleListByTag(String tag) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 设置条件：
        criteria.and("tags").is(tag);
        query.addCriteria(criteria);
        query.fields().exclude("content");
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public Page<Article> getArticlePageWithOutContentByTag(Integer pageNum, String tag) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 设置条件：
        criteria.and("tags").is(tag);
        query.addCriteria(criteria);
        query.fields().exclude("content");
        page(query,pageNum);
        int count =(int) mongoTemplate.count(query, Article.class);
        List<Article> articleList = mongoTemplate.find(query, Article.class);
        Integer totalPageNum = getTotalPageNum(count, pageSize);
        return new Page<Article>(pageNum,totalPageNum,articleList);
    }

    private void page(Query query,Integer pageNum){
        if (pageNum<1){
            throw new PageException("分页数据错误");
        }
        pageNum--;
        query.skip(pageSize*pageNum);
        query.limit(pageSize);
    }

    private Integer getTotalPageNum(int total,int pageSize){
        int temp=total/pageSize;
        if (total%pageSize!=0){
            return temp+1;
        }else {
            return temp;
        }
    }

}
