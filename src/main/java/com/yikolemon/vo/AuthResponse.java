package com.yikolemon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yikolemon
 * @date 2023/7/26 22:52
 * @description
 */



public class AuthResponse implements Serializable {

    int code;

    String msg;

    private AuthResponse() {
    }

    private AuthResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AuthResponse suc(){
        return new AuthResponse(200,null);
    }

    public static AuthResponse fail(int code,String msg){
        return new AuthResponse(code,msg);
    }

}
