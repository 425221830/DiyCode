package com.xiseven.diycode;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.util.logging.Level;

import static com.lzy.okgo.cache.CacheMode.NO_CACHE;
import static com.lzy.okgo.cache.CacheMode.REQUEST_FAILED_READ_CACHE;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public class DiyCodeApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initOkGo();
    }

    /**
     * 初始化网络请求框架
     */
    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        OkGo.init(this);
        try {
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //请求网络失败后，读取缓存
                    //.setCacheMode(REQUEST_FAILED_READ_CACHE)
                    .setCacheMode(NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)

                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates()                                  //方法一：信任所有证书,不安全有风险
                    //              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
                    //              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
                    //              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                    //               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                    //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
                    //               .setHostnameVerifier(new SafeHostnameVerifier())

                    //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
                    //                .addInterceptor(new Interceptor() {
                    //                    @Override
                    //                    public Response intercept(Chain chain) throws IOException {
                    //                        return chain.proceed(chain.request());
                    //                    }
                    //                })

                    //这两行同上，不需要就不要加入
                    .addCommonHeaders(headers)  //设置全局公共头
                    .addCommonParams(params);   //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Context getContext() {
        return mContext;
    }
}
