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
import com.xiseven.diycode.ui.activity.NodeActivity;
import com.xiseven.diycode.ui.activity.UserInfoActivity;
import com.xiseven.diycode.ui.activity.WebActivity;
import com.xiseven.diycode.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/5.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> implements IAdapter{


    private Context mContext;
    private List<News> newsList = new ArrayList<>();


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
        try {
            holder.tv_news_time.setText(DateUtils.getTimeAgo(newsList.get(position).getUpdated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //点击头像打开用户信息页面
        holder.iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                intent.putExtra("userLogin", newsList.get(position).getUser().getLogin());
                intent.putExtra("userName", newsList.get(position).getUser().getName());
                mContext.startActivity(intent);
            }
        });
        //点击用户名打开用户信息页面
        holder.tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                intent.putExtra("userLogin", newsList.get(position).getUser().getLogin());
                intent.putExtra("userName", newsList.get(position).getUser().getName());
                mContext.startActivity(intent);
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
        //打开该节点的内容
        holder.tv_news_nodename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NodeActivity.class);
                intent.putExtra("title", newsList.get(position).getNode_name());
                intent.putExtra("node_id", newsList.get(position).getNode_id());
                intent.putExtra("category", "news");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void setList(List list) {
        newsList = list;
        notifyDataSetChanged();
    }

    @Override
    public void notifyChange() {
        notifyDataSetChanged();
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
        @BindView(R.id.news_item)
        View news_item;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
