package com.yikolemon.util;

import com.google.gson.Gson;
import com.yikolemon.entity.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 部分map的key命名不好，使用util将之转化为所需的entity
 * @author yikolemon
 * @date 2023/8/1 23:02
 * @description
 */
public class Map2EntityUtil {

    /**
     * 通过dom4j获取的map，将其转化为我需要的Article类
     * @param map
     * @return
     */

    public static Article maptoArticle(Map<String,String> map) {
        //时间进行特殊处理注入
        String dateCreated = map.remove("dateCreated");
        //将description修改为content
        String description = map.get("description");
        map.remove("description");
        map.put("content",description);
        String postid = map.remove("postid");
        map.put("id",postid);
        Gson gson = new Gson();
        String str = gson.toJson(map);
        Article article = gson.fromJson(str, Article.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(dateCreated);
            article.setCreateTime(parse);
            return article;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}