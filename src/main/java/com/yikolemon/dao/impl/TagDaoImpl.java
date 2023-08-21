package com.yikolemon.dao.impl;

import com.yikolemon.dao.template.TagDao;
import com.yikolemon.entity.Tag;
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
public class TagDaoImpl implements TagDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Tag> getTagArticleCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("tags").count().as("num"),
                Aggregation.project("num").and("name").previousOperation());

        List<Tag> result = mongoTemplate.aggregate(aggregation,"article",Tag.class).getMappedResults();
        return result;
    }
}
