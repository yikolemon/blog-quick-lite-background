package com.yikolemon.dao.template;

import com.yikolemon.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 22:17
 * @description
 */
@Repository
public interface CategoryDao {

    public List<Category> getCategoryArticleCount();

}
