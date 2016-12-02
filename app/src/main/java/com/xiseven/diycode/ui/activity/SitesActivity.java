package com.xiseven.diycode.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.xiseven.diycode.R;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/2.
 */

public class SitesActivity extends BaseActivity {
    @BindView(R.id.web_sites)
    WebView web_sites;
    @Override
    public int getContentViewId() {
        return R.layout.activity_sites;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra("Url");

        web_sites.loadUrl(url);
    }
}
