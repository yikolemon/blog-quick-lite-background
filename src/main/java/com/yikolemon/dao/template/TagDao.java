package com.yikolemon.dao.template;

import com.yikolemon.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 22:17
 * @description
 */

public interface TagDao {

    public List<Tag> getTagArticleCount();

}
