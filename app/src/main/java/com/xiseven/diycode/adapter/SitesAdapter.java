package com.xiseven.diycode.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Sites;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesAdapter extends RecyclerView.Adapter<SitesAdapter.MyHolder> {
    private LayoutInflater inflater;
    private List<Sites> mData;
    Context mContext;

    public SitesAdapter(Context context, List<Sites> datas) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mData = datas;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_sites, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.recViewItemName.setText(mData.get(position).getName());
        holder.recViewItemSites.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.recViewItemSites.setAdapter(new SitesItemAdapter(mContext, mData.get(position).getSites()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recView_item_name)
        TextView recViewItemName;
        @BindView(R.id.recView_item_sites)
        RecyclerView recViewItemSites;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
