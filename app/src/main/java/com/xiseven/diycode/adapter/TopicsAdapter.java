package com.xiseven.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.ui.activity.NodeActivity;
import com.xiseven.diycode.ui.activity.TopicInfoActivity;
import com.xiseven.diycode.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.MyHolder> implements IAdapter{
    private Context mContext;
    private List<Topic> topicList = new ArrayList<>();
    private LayoutInflater inflater;

    public TopicsAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Picasso.with(mContext)
                .load(topicList.get(position).getUser().getAvatar_url())
                .into(holder.ivHead);
        holder.tvUsername.setText(topicList.get(position).getUser().getName());
        holder.tvTopicNodename.setText(topicList.get(position).getNode_name());
        holder.tvTopicNodename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NodeActivity.class);
                intent.putExtra("title", topicList.get(position).getNode_name());
                intent.putExtra("node_id", topicList.get(position).getNode_id());
                intent.putExtra("category", "topics");
                mContext.startActivity(intent);
            }
        });
        try {
            holder.tvTopicTime.setText(DateUtils.getTimeAgo(topicList.get(position).getUpdated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvTopicTitle.setText(topicList.get(position).getTitle());
        holder.tvTopicReplies.setText(""+topicList.get(position).getReplies_count());
        holder.topicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopicInfoActivity.class);
                intent.putExtra("topic", topicList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public void setList(List list) {
        topicList = list;
    }

    @Override
    public void notifyChange() {
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_topic_nodename)
        TextView tvTopicNodename;
        @BindView(R.id.tv_topic_time)
        TextView tvTopicTime;
        @BindView(R.id.tv_topic_title)
        TextView tvTopicTitle;
        @BindView(R.id.tv_topic_replies)
        TextView tvTopicReplies;
        @BindView(R.id.topic_item)
        CardView topicItem;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
