package com.yikolemon.dao.impl;

import com.yikolemon.dao.template.CategoryDao;
import com.yikolemon.entity.Article;
import com.yikolemon.entity.Category;
import com.yikolemon.entity.Page;
import com.yikolemon.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
    public List<Tag> getCategoryArticleCount() {

        //目的：查询出每个班里有多少姓张的男生，并用班级代号分组输出
//.group("class").count().as("count")就是以班级分组，计算数量，数量命名为“count”
//project中表示需要输出的字段，.and.as就是“classId”这个字段也要输出，字段名为“cId”,.and("cId").previousOperation就是以“cId”作为分组的“_id”输出)

        //Criteria criteria = Criteria.where("category").is("学习笔记");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("category").count().as("num"),
                Aggregation.project("num").and("name").previousOperation());

        List<Category> result = mongoTemplate.aggregate(aggregation,"article",Category.class).getMappedResults();
        System.out.println("fuck");

        return null;
    }
}
