package com.yikolemon.dao.template;

import com.yikolemon.entity.Article;
import com.yikolemon.entity.Page;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yikolemon
 * @date 2023/8/20 18:10
 * @description
 */

public interface ArticleDao{

    List<Article> getArticleListWithOutContent();

    Page<Article> getArticlePageWithOutContent(Integer pageNum);

    List<Article> getArticleListByCategory(String category);

    Page<Article> getArticlePageWithOutContentByCategory(Integer pageNum,String category);

    List<Article> getArticleListByTag(String tag);
    Page<Article> getArticlePageWithOutContentByTag(Integer pageNum,String tag);

}
