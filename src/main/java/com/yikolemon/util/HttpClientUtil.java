package com.yikolemon.util;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.util.concurrent.TimeUnit;

public class HttpClientUtil {

    public static RequestConfig getTimeOutRequestConfig(){
        return RequestConfig.custom()
                .setConnectionRequestTimeout(1, TimeUnit.MINUTES)
                .setResponseTimeout(1,TimeUnit.MINUTES)
                .build();
    }
}
