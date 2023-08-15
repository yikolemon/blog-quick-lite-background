package com.yikolemon.dao.cnblogs;

import cn.hutool.core.io.IoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yikolemon.entity.Article;
import com.yikolemon.entity.AuthToken;
import com.yikolemon.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Component
public class Oauth2BlogClient {

    private static final String CNBLOGS_URI = "https://api.cnblogs.com/api";

    @Value("${cnblogs.username}")
    private String userName;

    @Value("${tempToken}")
    private String oauth2Token;

    /**
     * 获取所有的博客列表（无内容,分类,标签）
     *
     * @return
     */
    public List<Article> getAllArticleList() {
        int curPage = 1;
        boolean hasNextPage = true;
        CloseableHttpClient client = HttpClients.createDefault();
        ArrayList<Article> cnblogsArticleList = new ArrayList<>();
        while (hasNextPage) {
            StringBuilder builder = new StringBuilder(CNBLOGS_URI);
            builder.append("/blogs/");
            builder.append(userName);
            builder.append("/posts/?pageIndex=");
            builder.append(curPage);
            HttpGet httpGet = new HttpGet(builder.toString());
            httpGet.setConfig(HttpClientUtil.getTimeOutRequestConfig());
//            httpGet.addHeader("Authorization", "Bearer " + AuthToken.access_token);
            httpGet.addHeader("Authorization", "Bearer " + oauth2Token);

            try {
                CloseableHttpResponse response = client.execute(httpGet);
                int code = response.getCode();
                if (code != 200) {
                    log.error(builder.toString() + "===>" + code);
                } else {
                    HttpEntity entity = response.getEntity();
                    try (InputStream inputStream = entity.getContent();
                         InputStreamReader reader = new InputStreamReader(inputStream)){
                        String responseJson = IoUtil.read(reader);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        Article[] articles =gson.fromJson(responseJson, Article[].class);
                        if (articles != null && articles.length > 0) {
                            //说明反序列化到了Article
                            for (Article a : articles) {
                                cnblogsArticleList.add(a);
                            }
                        } else {
                            hasNextPage = false;
                        }
                    }
                }
                curPage++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cnblogsArticleList;
    }

}
