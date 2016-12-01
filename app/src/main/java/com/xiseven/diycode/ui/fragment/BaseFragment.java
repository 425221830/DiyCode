package com.xiseven.diycode.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiseven.diycode.ui.iView.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    private View view;

    /**
     * 子类重写该方法，实现设置布局
     *
     * @return
     */
    public abstract int getContentViewId();
    /**
     * 子类重写该方法，初始化view
     * @param savedInstanceState
     */
    protected abstract void initAllMembers(View view, Bundle savedInstanceState);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getContentViewId(), null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAllMembers(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showSnackbar(CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(CharSequence text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }
}










































