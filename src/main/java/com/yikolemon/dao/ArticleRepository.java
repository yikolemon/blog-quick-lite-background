package com.yikolemon.dao;

import com.yikolemon.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 继承 MongoRepository<实体类，主键类型>,以实现CRUD
 **/

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {


	//根据id查询文章
    Article findByid(String id);


}
