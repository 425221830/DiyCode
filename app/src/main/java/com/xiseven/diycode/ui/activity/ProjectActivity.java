package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Project;
import com.xiseven.diycode.utils.DateUtils;
import com.zzhoujay.glideimagegetter.GlideImageGetter;
import com.zzhoujay.markdown.MarkDown;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.text.ParseException;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/10.
 */
public class ProjectActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_project_category)
    TextView tvProjectCategory;
    @BindView(R.id.tv_project_sub_category)
    TextView tvProjectSubCategory;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_project_des)
    TextView tvProjectDes;
    @BindView(R.id.tv_project_readme)
    TextView tvProjectReadme;
    @BindView(R.id.tv_project_time)
    TextView tvProjectTime;
    @BindView(R.id.tv_project_github)
    TextView tvProjectGithub;
    @BindView(R.id.tv_project_website)
    TextView tvProjectWebsite;
    private Project project;

    @Override
    public int getContentViewId() {
        return R.layout.activity_project;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        project = getIntent().getParcelableExtra("project");
        initToolbar(project.getName());
        Picasso.with(mActivity)
                .load(project.getProject_cover_url())
                .into(ivHead);
        tvName.setText(project.getName());
        tvProjectCategory.setText(project.getCategory().getName());
        tvProjectSubCategory.setText(project.getSub_category().getName());
        tvProjectDes.setText(project.getDescription());
        tvProjectGithub.setText(Html.fromHtml("<u>" + "Github" + "</u>"));
        tvProjectGithub.setOnClickListener(this);
        tvProjectWebsite.setText(Html.fromHtml("<u>" + "Website" + "</u>"));
        tvProjectWebsite.setOnClickListener(this);
        try {
            tvProjectTime.setText(DateUtils.getTimeAgo(project.getLast_updated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RichText.fromMarkdown(project.getReadme())
                .error(R.mipmap.icon)
                .bind(mActivity)
                .imageGetter(new GlideImageGetter())
                .urlClick(new OnUrlClickListener() {
                    @Override
                    public boolean urlClicked(String url) {
                        Intent intent = new Intent(mActivity, WebActivity.class);
                        intent.putExtra("title", "diycode");
                        intent.putExtra("Url", url);
                        startActivity(intent);
                        return true;
                    }
                })
                .into(tvProjectReadme);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_project_website:
                if (project.getWebsite().isEmpty()) {
                    showToast("没有数据");
                } else {
                    Intent intent = new Intent(mActivity, WebActivity.class);
                    intent.putExtra("Url", project.getWebsite());
                    intent.putExtra("title", project.getName());
                    startActivity(intent);
                }
                break;
            case R.id.tv_project_github:
                if (project.getGithub().isEmpty()) {
                    showToast("没有数据");
                } else {
                    Intent intent = new Intent(mActivity, WebActivity.class);
                    intent.putExtra("Url", project.getGithub());
                    intent.putExtra("title", project.getName());
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        RichText.clear(mActivity);
        super.onDestroy();
    }
}
