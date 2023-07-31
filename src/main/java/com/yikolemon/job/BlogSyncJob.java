package com.yikolemon.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yikolemon
 * @date 2023/7/31 22:29
 * @description
 */

@EnableScheduling//开启定时任务
@Component
@Slf4j
public class BlogSyncJob {

    /**
     * 同步博客到本地数据库，并保存本地文件
     */
    @Scheduled(cron="0 0/150 * * * *")//每两个半小时执行一次
    public void syncBlog(){
        
    }

}
