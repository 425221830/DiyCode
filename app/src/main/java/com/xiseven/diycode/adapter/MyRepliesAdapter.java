package com.xiseven.diycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.MyReplies;
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

public class MyRepliesAdapter extends RecyclerView.Adapter<MyRepliesAdapter.MyHolder> {
    private Context mContext;
    private List<MyReplies> list = new ArrayList<>();
    private LayoutInflater inflater;

    public void setList(List<MyReplies> list) {
        this.list = list;
    }

    public MyRepliesAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_myreplies, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTopic_title());
        RichText.fromHtml(list.get(position).getBody_html())
                .into(holder.tvBody);
        try {
            holder.tvTime.setText(DateUtils.getTimeAgo(list.get(position).getCreated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.itemMyReplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopicInfoActivity.class);
                intent.putExtra("topic_id", list.get(position).getTopic_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_body)
        TextView tvBody;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.item_myreplies)
        View itemMyReplies;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
