package com.yikolemon.job;

import com.google.gson.Gson;
import com.yikolemon.entity.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author yikolemon
 * @date 2023/7/27 22:04
 * @description
 */
@EnableScheduling//开启定时任务
@Component
@Slf4j
public class AuthJob {

    private static DateTimeFormatter pattern=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");

    @Value("${cnblogs.oauth2.client_id}")
    private  String clientId = null;

    @Value("${cnblogs.oauth2.client_secret}")
    private String clientSecret = null;

    static int retry=0;

    /**
     * 刷新token（3小时过期，2小时半刷新一次）
     */
    @Scheduled(cron="0 0/150 * * * *")//每两个半小时执行一次
//    @Scheduled(cron="0/5 * * * * *")//每5s执行一次
    public void refreshToken(){
        //refresh token存在，进行刷新操作
        if (!StringUtils.isEmpty(AuthToken.refresh_token)){
            //注意access_token要加锁，防止更新时使用出错
            synchronized (AuthToken.access_token){
                String uri="https://api.cnblogs.com/token";
                HttpPost httpPost = new HttpPost(uri);
                ArrayList<NameValuePair> body = new ArrayList<>();
                body.add(new BasicNameValuePair("client_id",clientId));
                body.add(new BasicNameValuePair("client_secret",clientSecret));
                body.add(new BasicNameValuePair("grant_type","refresh_token"));
                body.add(new BasicNameValuePair("refresh_token",AuthToken.refresh_token));
                body.add(new BasicNameValuePair("redirect_uri","https://oauth.cnblogs.com/auth/callback"));
                httpPost.setEntity(new UrlEncodedFormEntity(body));
                try (CloseableHttpClient client= HttpClients.createDefault()){
                    try (CloseableHttpResponse response = client.execute(httpPost)) {
                        int resCode = response.getCode();
                        if (resCode!=200){
                            //出错
                            log.error(EntityUtils.toString(response.getEntity()));
                            //出错重试
                            if (retry>3){
                                retry=0;
                            }else {
                                //重试
                                refreshToken();
                                retry++;
                            }
                        }else {
                            HttpEntity entity = response.getEntity();
                            String res = EntityUtils.toString(entity);
                            //System.out.println(res);
                            Map<String,String> resMap= new Gson().fromJson(res, Map.class);
                            //登录成功了，在本地存储access_token和refresh_token；
                            AuthToken.access_token=resMap.get("access_token");
                            AuthToken.refresh_token=resMap.get("refresh_token");
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}


