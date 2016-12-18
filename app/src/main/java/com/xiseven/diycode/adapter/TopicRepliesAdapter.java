package com.xiseven.diycode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.TopicReplies;
import com.xiseven.diycode.ui.activity.TopicInfoActivity;
import com.xiseven.diycode.utils.DateUtils;
import com.zzhoujay.richtext.RichText;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/17.
 */

public class TopicRepliesAdapter extends RecyclerView.Adapter<TopicRepliesAdapter.MyHolder>{

    private Context mContext;
    private List<TopicReplies> repliesList = new ArrayList<>();
    private LayoutInflater inflater;
    private TopicInfoActivity activity;

    public void setRepliesList(List<TopicReplies> repliesList) {
        this.repliesList = repliesList;
    }

    public TopicRepliesAdapter(Context context) {
        activity = (TopicInfoActivity) context;
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_topic_replie, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Picasso.with(mContext)
                .load(repliesList.get(position).getUser().getAvatar_url())
                .into(holder.ivHead);
        holder.tvUsername.setText(repliesList.get(position).getUser().getName());
        try {
            holder.tvTime.setText(DateUtils.getTimeAgo(repliesList.get(position).getUpdated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RichText.fromHtml(repliesList.get(position).getBody_html()).into(holder.tvReplies);
        holder.ivReplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.replies();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repliesList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_replies)
        TextView tvReplies;
        @BindView(R.id.iv_replies)
        ImageView ivReplies;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
