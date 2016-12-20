package com.xiseven.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Notification;
import com.xiseven.diycode.ui.activity.TopicInfoActivity;
import com.xiseven.diycode.utils.DateUtils;
import com.zzhoujay.richtext.RichText;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
    private Context mContext;
    private List<Notification> notifications = new ArrayList<>();
    private LayoutInflater inflater;

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public NotificationAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Picasso.with(mContext)
                .load(notifications.get(position).getActor().getAvatar_url())
                .into(holder.ivHead);
        holder.tvTitle.setText(notifications.get(position).getActor().getLogin() + "在"
                + notifications.get(position).getMention().getId() + "中提到你");
        try {
            holder.tvTime.setText(DateUtils.getTimeAgo(notifications.get(position).getCreated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RichText.fromHtml(notifications.get(position).getMention().getBody_html())
                .into(holder.tvBody);
        holder.itemNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopicInfoActivity.class);
                intent.putExtra("topic_id", notifications.get(position).getMention().getTopic_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_body)
        TextView tvBody;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.itme_notification)
        View itemNotification;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
