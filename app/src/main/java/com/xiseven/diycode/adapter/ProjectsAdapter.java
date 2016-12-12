package com.xiseven.diycode.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.Project;
import com.xiseven.diycode.ui.activity.ProjectActivity;
import com.xiseven.diycode.ui.activity.WebActivity;
import com.xiseven.diycode.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/12/7.
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyHolder> {
    private Context mContext;
    private List<Project> projectList = new ArrayList<>();
    private LayoutInflater inflater;

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public ProjectsAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.rec_item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        Picasso.with(mContext)
                .load(projectList.get(position).getProject_cover_url())
                .into(holder.ivHead);
        holder.tvName.setText(projectList.get(position).getName());
        holder.tvProjectCategory.setText(projectList.get(position).getCategory().getName());
        holder.tvProjectSubCategory.setText(projectList.get(position).getSub_category().getName());
        holder.tvProjectDes.setText(projectList.get(position).getDescription());
        try {
            holder.tvProjectTime.setText(DateUtils.getTimeAgo(projectList.get(position).getLast_updated_at()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.projectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProjectActivity.class);
                //对project进行Parcelable序列化，直接传递这个对象
                intent.putExtra("project", projectList.get(position));
                mContext.startActivity(intent);
//                if (Build.VERSION.SDK_INT > 20) {
//                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity)mContext,
//                            Pair.create((View)holder.ivHead, "iv_head"),
//                            Pair.create((View)holder.tvName,"tv_name"),
//                            Pair.create((View)holder.projectItem,"project_item"),
//                            Pair.create((View)holder.tvProjectSubCategory,"tv_project_sub_category"),
//                            Pair.create((View)holder.tvProjectCategory,"tv_project_category"),
//                            Pair.create((View)holder.tvProjectTime,"tv_project_time"),
//                            Pair.create((View)holder.tvProjectDes,"tv_project_des")
//                    ).toBundle());
//                } else {
//                    mContext.startActivity(intent);
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_project_category)
        TextView tvProjectCategory;
        @BindView(R.id.tv_project_sub_category)
        TextView tvProjectSubCategory;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_project_des)
        TextView tvProjectDes;
        @BindView(R.id.tv_project_time)
        TextView tvProjectTime;
        @BindView(R.id.project_item)
        CardView projectItem;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
