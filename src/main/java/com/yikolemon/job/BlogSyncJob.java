package com.yikolemon.job;

import com.yikolemon.dao.ArticleRepository;
import com.yikolemon.dao.cnblogs.MetaWbelogClient;
import com.yikolemon.dao.cnblogs.Oauth2BlogClient;
import com.yikolemon.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author yikolemon
 * @date 2023/7/31 22:29
 * @description
 */

@EnableScheduling//开启定时任务
@Component
@Slf4j
public class BlogSyncJob {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private Oauth2BlogClient oauth2BlogClient;

    @Autowired
    private MetaWbelogClient metaWbelogClient;

    /**
     * 同步博客到本地数据库，并保存本地文件
     */
    @Scheduled(cron="0 0/180 * * * *")//每三小时执行一次
    public void syncBlog(){
        List<Article> cnblogsArticleList = oauth2BlogClient.getAllArticleList();
        List<Article> localArticleList = articleRepository.findAll();
        HashMap hashMap = new HashMap<>();
        for (Article localArticle : localArticleList) {
            hashMap.put(localArticle.getId(),localArticle);
        }
        for (Article cnblogsArticle : cnblogsArticleList) {
            if (!hashMap.containsKey(cnblogsArticle.getId())){
                //说明这个不存在，需要更新进入数据库
                Article post = metaWbelogClient.getPost(cnblogsArticle.getId());
                //注入content
                cnblogsArticle.setContent(post.getContent());
                //需要注入其他信息


                articleRepository.insert(cnblogsArticle);
            }
        }
    }

}
