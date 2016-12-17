package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.TopicRepliesAdapter;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.bean.TopicReplies;
import com.xiseven.diycode.ui.iView.ITopicInfoView;
import com.xiseven.diycode.ui.presenter.TopicInfoPresenter;
import com.xiseven.diycode.utils.DateUtils;
import com.zzhoujay.glideimagegetter.GlideImageGetter;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.text.ParseException;
import java.util.List;

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
    @BindView(R.id.rec_replies)
    PullLoadMoreRecyclerView recReplies;
    private Topic topic;
    private TopicInfoPresenter mPresenter;
    private FloatingActionButton fabReplies;
    private TopicRepliesAdapter repliesAdapter;
    private int i = 1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_topicinfo;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        topic = getIntent().getParcelableExtra("topic");
        mPresenter = new TopicInfoPresenter(this);
        initToolbar("社区话题");
        initFab();
        initRec();
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

    private void initRec() {
        recReplies.setLinearLayout();
        if (repliesAdapter == null) {
            repliesAdapter = new TopicRepliesAdapter(mActivity);
        }
        recReplies.setAdapter(repliesAdapter);
        mPresenter.getTopicReplies(topic.getId(), 20);
        recReplies.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
            }
        });
    }

    private void initFab() {
        fabReplies = (FloatingActionButton) findViewById(R.id.fab_replies);
        fabReplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("replies");
            }
        });
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
    public void setTopicRepliesAdapter(List<TopicReplies> repliesList) {
        repliesAdapter.setRepliesList(repliesList);
        repliesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        RichText.clear(mActivity);
        super.onDestroy();
    }
}
