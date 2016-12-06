package com.xiseven.diycode.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xiseven.diycode.R;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/2.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.wv_web)
    WebView wv_web;
    @BindView(R.id.pb_web)
    ProgressBar pb_web;
    private WebSettings settings;
    private String url;

    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        Toolbar toolbar = initToolbar(getIntent().getStringExtra("title"));
        url = getIntent().getStringExtra("Url");
        initWebView();
        wv_web.loadUrl(url);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        settings = wv_web.getSettings();

        //支持获取手势焦点，输入用户名、密码或其他
        wv_web.requestFocusFromTouch();
        wv_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });
        wv_web.setWebChromeClient(new WebChromeClient() {

            //获得网页的加载进度，显示在右上角的TextView控件中
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_web.setProgress(newProgress);
                if (newProgress == pb_web.getMax()) {
                    pb_web.setVisibility(View.GONE);
                } else {
                    pb_web.setVisibility(View.VISIBLE);
                }

            }

        });
        settings.setJavaScriptEnabled(true);  //支持js
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_web.canGoBack()) {
            wv_web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                shareIntent.putExtra(Intent.EXTRA_TEXT, wv_web.getUrl());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        if (wv_web != null) {
            wv_web.destroy();
        }
        super.onDestroy();
    }
}
