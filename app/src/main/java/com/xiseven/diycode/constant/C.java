package com.xiseven.diycode.constant;

/**
 * 保存全局常量
 * Created by XISEVEN on 2016/11/23.
 */

public class C {
    public static String client_id = "77aef253";
    public static String client_secret = "8861cbb533d1c19caa6fba43a9351405259068fc52ba3cae744a84df98c3a842";
    public static String baseUrl = "http://www.diycode.cc/api/v3/";

    public static String RequestHeaderKey="Authorization";
    public static String getRequestHeaderValue(String token) {
        return "Bearer " + token;
    }
    //delete请求退出登录
    public static String signOutUrl="http://www.diycode.cc/oauth/authorize";


}
