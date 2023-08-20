package com.yikolemon.dao.impl;

import com.yikolemon.dao.template.TagDao;
import com.yikolemon.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 22:19
 * @description
 */
@Repository
public class TagDaoImpl implements TagDao {
    @Override
    public List<Tag> getTagArticleCount() {
        return null;
    }
}
