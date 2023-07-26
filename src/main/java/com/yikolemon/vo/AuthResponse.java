package com.yikolemon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yikolemon
 * @date 2023/7/26 22:52
 * @description
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {

    int code;

    String msg;

    String token;

    public static AuthResponse suc(String token){
        return new AuthResponse(200,null,token);
    }

    public static AuthResponse fail(int code,String msg){
        return new AuthResponse(code,msg,null);
    }

}