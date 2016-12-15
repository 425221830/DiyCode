package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.ui.iView.ITopicInfoView;
import com.xiseven.diycode.ui.presenter.TopicInfoPresenter;
import com.xiseven.diycode.utils.DateUtils;
import com.zzhoujay.glideimagegetter.GlideImageGetter;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.text.ParseException;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicInfoActivity extends BaseActivity implements ITopicInfoView {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_topic_nodename)
    TextView tvTopicNodename;
    @BindView(R.id.tv_topic_time)
    TextView tvTopicTime;
    @BindView(R.id.tv_topic_title)
    TextView tvTopicTitle;
    @BindView(R.id.tv_topic_body)
    TextView tvTopicBody;
    private Topic topic;
    private TopicInfoPresenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_topic;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        topic = getIntent().getParcelableExtra("topic");
        initToolbar("社区话题");
        mPresenter = new TopicInfoPresenter(this);
        Picasso.with(mActivity)
                .load(topic.getUser().getAvatar_url())
                .into(ivHead);
        tvUsername.setText(topic.getUser().getName());
        tvTopicNodename.setText(topic.getNode_name());
        tvTopicTitle.setText(topic.getTitle());
        try {
            tvTopicTime.setText(DateUtils.getTimeAgo(topic.getUpdated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mPresenter.getTopicBody(topic.getId());
    }

    @Override
    public void setTopicBody(String body) {
        RichText.fromHtml(body)
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
                .into(tvTopicBody);

    }

    @Override
    protected void onDestroy() {
        RichText.clear(mActivity);
        super.onDestroy();
    }
}
