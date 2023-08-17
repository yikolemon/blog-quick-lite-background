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

import java.util.ArrayList;
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
//    @Scheduled(cron="0 0/180 * * * *")//每三小时执行一次
    @Scheduled(cron="0/10 * * * * *")
    public void syncBlog(){
        List<String> allArticleIdList = oauth2BlogClient.getAllArticleIdList();
        List<Article> localArticleList = articleRepository.findAll();
        HashMap hashMap = new HashMap<>();
        for (Article localArticle : localArticleList) {
            hashMap.put(localArticle.getId(),localArticle);
        }
        ArrayList<Article> addList = new ArrayList<>();
        for (String blogId : allArticleIdList) {
            Article article = metaWbelogClient.getPost(blogId);
            if (!hashMap.containsKey(blogId)){
                //说明这个不存在，需要更新进入数据库
                //需要注入其他信息
                addList.add(article);
            }else{
                //存在则比较
                if (!article.equals(hashMap.get(blogId))){
                    articleRepository.deleteById(blogId);
                    addList.add(article);
                }
            }
        }
        articleRepository.insert(addList);
    }

}
