package com.yikolemon.controller;


import com.google.gson.Gson;
import com.yikolemon.entity.AuthToken;
import com.yikolemon.vo.AuthResponse;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * @author yikolemon
 * @date 2023/7/24 22:34
 * @description
 */

@Controller
@RequestMapping("/auth")
public class Oauth2Controller {

    @Value("${cnblogs.oauth2.client_id}")
    private  String clientId = null;

    @Value("${cnblogs.oauth2.client_secret}")
    private String clientSecret = null;

    String accessTokenUrl = null;
    String userInfoUrl = null;

    String scpoe=null;

    String redirectUrl = null;
    String response_type = null;
    String code= null;

    //跳转oauth2认证服务器认证，授权码模式，第一步，获取code
    @GetMapping("/getCode")
    public String oauth2GetCode(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws URISyntaxException {
        //构建URI
        URIBuilder uriBuilder = new URIBuilder("https://oauth.cnblogs.com/connect/authorize");
        uriBuilder.addParameter("client_id",clientId);
        uriBuilder.addParameter("scope","openid profile CnBlogsApi offline_access");
        uriBuilder.addParameter("response_type","code id_token");
        uriBuilder.addParameter("redirect_uri","https://oauth.cnblogs.com/auth/callback");
        uriBuilder.addParameter("nonce",UUID.randomUUID().toString());
        URI uri = uriBuilder.build();
        return "redirect:" + uri.toString();
    }

    //通过认证得到的code，获取access_token
    //授权码模式第二步
    //接受客户端返回的code，提交申请access token的请求
    @RequestMapping(value="/getToken",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody String code){
        Map<String,String> map = new Gson().fromJson(code, Map.class);
        code = map.get("code");
        String uri="https://api.cnblogs.com/token";
        HttpPost httpPost = new HttpPost(uri);
        ArrayList<NameValuePair> body = new ArrayList<>();
        body.add(new BasicNameValuePair("client_id",clientId));
        body.add(new BasicNameValuePair("client_secret",clientSecret));
        body.add(new BasicNameValuePair("grant_type","authorization_code"));
        body.add(new BasicNameValuePair("code",code));
        body.add(new BasicNameValuePair("redirect_uri","https://oauth.cnblogs.com/auth/callback"));
        httpPost.setEntity(new UrlEncodedFormEntity(body));
        try (CloseableHttpClient client=HttpClients.createDefault()){
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                int resCode = response.getCode();
                if (resCode!=200){
                    //出错
                    return AuthResponse.fail(500,EntityUtils.toString(response.getEntity()));
                }else {
                    HttpEntity entity = response.getEntity();
                    String res = EntityUtils.toString(entity);
                    //System.out.println(res);
                    Map<String,String> resMap= new Gson().fromJson(res, Map.class);
                    //登录成功了，在本地存储access_token和refresh_token；
                    AuthToken.access_token=resMap.get("access_token");
                    AuthToken.refresh_token=resMap.get("refresh_token");
                    return AuthResponse.suc();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
