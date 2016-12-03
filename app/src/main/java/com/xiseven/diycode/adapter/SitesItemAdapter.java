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
import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.ui.activity.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/2.
 */

public class SitesItemAdapter extends RecyclerView.Adapter<SitesItemAdapter.MyHolder> {
    List<Sites.SitesBean> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public SitesItemAdapter(Context context, List<Sites.SitesBean> list) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_sites_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tv_sites_name.setText(mList.get(position).getName());
        Picasso.with(mContext)
                .load(mList.get(position).getAvatar_url())
                .into(holder.iv_sites_icon);
        holder.sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("Url", mList.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sites_icon)
        ImageView iv_sites_icon;
        @BindView(R.id.tv_sites_name)
        TextView tv_sites_name;
        @BindView(R.id.ll_sites)
        View sites;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
