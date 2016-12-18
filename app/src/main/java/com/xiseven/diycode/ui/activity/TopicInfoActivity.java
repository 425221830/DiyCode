package com.xiseven.diycode.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.MyViewPagerAdapter;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicInfoActivity extends BaseActivity implements ITopicInfoView {

    private View vpBody, vpReplies;
    @BindView(R.id.vp)
    ViewPager vp;
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
    TextView tvTopicBody;
    PullLoadMoreRecyclerView recReplies;
    LinearLayout layoutReplies;
    TextInputEditText etReplies;
    ImageView ivSubmit;
    private List<String> mTitleList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
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
        initVp();
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

    private void initVp() {
        vpBody = LayoutInflater.from(mActivity).inflate(R.layout.vp_topic_body, null);
        tvTopicBody = (TextView) vpBody.findViewById(R.id.tv_topic_body);
        vpReplies = LayoutInflater.from(mActivity).inflate(R.layout.vp_topic_replies, null);
        recReplies = (PullLoadMoreRecyclerView) vpReplies.findViewById(R.id.rec_replies);
        fabReplies = (FloatingActionButton) vpReplies.findViewById(R.id.fab_replies);
        layoutReplies = (LinearLayout) vpReplies.findViewById(R.id.layout_replies);
        etReplies = (TextInputEditText) vpReplies.findViewById(R.id.et_replies);
        ivSubmit = (ImageView) vpReplies.findViewById(R.id.iv_submit);

        mViewList.add(vpBody);
        mViewList.add(vpReplies);
        mTitleList.add("内容");
        mTitleList.add("评论");
        vp.setAdapter(new MyViewPagerAdapter(mTitleList, mViewList));
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
        fabReplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replies();
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
    public void onBackPressed() {
        if (layoutReplies.getVisibility() == View.VISIBLE) {
            layoutReplies.setVisibility(View.GONE);
            fabReplies.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        RichText.clear(mActivity);
        super.onDestroy();
    }

    public void replies() {
        layoutReplies.setVisibility(View.VISIBLE);
        fabReplies.setVisibility(View.GONE);
        etReplies.requestFocus();
        InputMethodManager imm = (InputMethodManager) etReplies.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }
}
