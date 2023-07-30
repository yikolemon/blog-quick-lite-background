package com.yikolemon.dao.cnblogs;

import com.yikolemon.util.MetaWeblogUtil;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yikolemon
 * @date 2023/7/30 22:51
 * @description
 */
@Component
public class MetaWbelogClient {


    @Value("${cnblogs.url}")
    private String url;

    @Value("${cnblogs.username}")
    private String username;


    @Value("${cnblogs.token}")
    private String token;

    public String getPost(String id){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type","application/xml");
        ArrayList<NameValuePair> list = new ArrayList<>();
        String body = MetaWeblogUtil.getBody(new String[]{id, username, token});
        httpPost.setEntity(new StringEntity(body));
        try {
            try (CloseableHttpClient client= HttpClients.createDefault()) {
                try (CloseableHttpResponse response = client.execute(httpPost)) {
                    int resCode = response.getCode();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
