package com.yikolemon.controller;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yikolemon
 * @date 2023/7/24 22:34
 * @description
 */

@Component
public class Oauth2Controller {

    @Value("${cnblos}")

    private  String clientId = null;
    private String clientSecret = null;
    String accessTokenUrl = null;
    String userInfoUrl = null;

    String scpoe=null;

    String redirectUrl = null;
    String response_type = null;
    String code= null;

    //跳转oauth2认证服务器认证，获取code
    @RequestMapping("/requestServerCode")
    public String requestServerFirst(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws OAuthProblemException {
        String requestUrl=null;
//        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        String requestUrl = null;
//        scpoe="openid profile CnBlogsApi offline_access";
//        try {
//            //构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
//            OAuthClientRequest accessTokenRequest = OAuthClientRequest
//                    .authorizationLocation(accessTokenUrl)
//                    .setClientId(clientId)
//                    .setScope(scpoe)
//                    .setResponseType(response_type)
//                    .setRedirectURI(redirectUrl)
//                    .buildQueryMessage();
//            requestUrl = accessTokenRequest.getLocationUri();
//            System.out.println(requestUrl);
//        } catch (OAuthSystemException e) {
//            e.printStackTrace();
//        }
        return "redirect:" + requestUrl;
    }

    //通过认证得到的code，获取access_token
    //接受客户端返回的code，提交申请access token的请求
    @RequestMapping("/callbackCode")
    public Object toLogin(HttpServletRequest request){
//
//        System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//        clientId = "clientId";
//        clientSecret = "clientSecret";
//        //accessTokenUrl="http://localhost:8082/oauthserver/responseAccessToken";
//        userInfoUrl = "userInfoUrl";
//        //redirectUrl = "http://localhost:8081/oauthclient01/server/accessToken";
//        HttpServletRequest httpRequest = (HttpServletRequest)request;
//        code = httpRequest.getParameter("code");
//        System.out.println(code);
//        OAuthClient oAuthClient =new OAuthClient(new URLConnectionClient());
//
//        try {
//            OAuthClientRequest accessTokenRequest = OAuthClientRequest
//                    .tokenLocation(accessTokenUrl)
//                    .setGrantType(GrantType.AUTHORIZATION_CODE)
//                    .setClientId(clientId)
//                    .setClientSecret(clientSecret)
//                    .setCode(code)
//                    .setRedirectURI(redirectUrl)
//                    .buildQueryMessage();
//
//            //去服务端请求access token，并返回响应
//            OAuthAccessTokenResponse oAuthResponse =oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
//            //获取服务端返回过来的access token
//            String accessToken = oAuthResponse.getAccessToken();
//
//            //查看access token是否过期
//            Long expiresIn =oAuthResponse.getExpiresIn();
//
//            System.out.println("客户端/callbackCode方法的token：：："+accessToken);
//            System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//
//            return"redirect:http://localhost:8081/oauthclient01/server/accessToken?accessToken="+accessToken;
//
//        } catch (OAuthSystemException e) {
//            e.printStackTrace();
//        }

        return null;
    }

}
