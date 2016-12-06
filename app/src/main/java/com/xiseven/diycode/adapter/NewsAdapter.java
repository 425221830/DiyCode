package com.xiseven.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.News;
import com.xiseven.diycode.ui.activity.UserInfoActivity;
import com.xiseven.diycode.ui.activity.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/5.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {


    private Context mContext;
    private List<News> newsList = new ArrayList<>();


    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    private LayoutInflater inflater;

    public NewsAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        Picasso.with(mContext)
                .load(newsList.get(position).getUser().getAvatar_url())
                .into(holder.iv_head);
        holder.tv_username.setText(newsList.get(position).getUser().getName());
        holder.tv_news_nodename.setText(newsList.get(position).getNode_name());
        holder.tv_news_title.setText(newsList.get(position).getTitle());

        //点击头像打开用户信息页面
        holder.iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                intent.putExtra("userLogin", newsList.get(position).getUser().getLogin());
                mContext.startActivity(intent);
            }
        });
        //点击用户名打开用户信息页面
        holder.tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                intent.putExtra("userLogin", newsList.get(position).getUser().getLogin());
                mContext.startActivity(intent);
            }
        });
        //点击查看评论打开评论页面
        holder.tv_news_replies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //点赞
        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //打开news源地址
        holder.news_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("Url", newsList.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView iv_head;
        @BindView(R.id.tv_username)
        TextView tv_username;
        @BindView(R.id.tv_news_nodename)
        TextView tv_news_nodename;
        @BindView(R.id.tv_news_time)
        TextView tv_news_time;
        @BindView(R.id.tv_news_title)
        TextView tv_news_title;
        @BindView(R.id.tv_news_replies)
        TextView tv_news_replies;
        @BindView(R.id.btn_like)
        Button btn_like;
        @BindView(R.id.news_item)
        View news_item;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}