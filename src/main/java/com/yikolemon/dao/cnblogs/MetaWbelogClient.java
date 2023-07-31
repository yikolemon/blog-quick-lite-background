package com.yikolemon.dao.cnblogs;

import com.google.gson.Gson;
import com.yikolemon.entity.AuthToken;
import com.yikolemon.util.MetaWeblogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author yikolemon
 * @date 2023/7/30 22:51
 * @description
 */
@Component
@Slf4j
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
                    if (resCode!=200){
                        //出错
                        log.error(EntityUtils.toString(response.getEntity()));
                    }else {
                        HttpEntity entity = response.getEntity();
                        String xml = EntityUtils.toString(entity);
                        Document document = DocumentHelper.parseText(xml);
                        Element rootElement = document.getRootElement();
                        System.out.println(rootElement.getTextTrim());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
