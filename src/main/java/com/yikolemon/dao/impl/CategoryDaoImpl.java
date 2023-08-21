package com.yikolemon.dao.impl;

import com.yikolemon.dao.template.CategoryDao;
import com.yikolemon.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 22:19
 * @description
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Category> getCategoryArticleCount() {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("category").count().as("num"),
                Aggregation.project("num").and("name").previousOperation());

        List<Category> result = mongoTemplate.aggregate(aggregation,"article",Category.class).getMappedResults();
        System.out.println("fuck");

        return null;
    }
}
