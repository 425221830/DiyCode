package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xiseven.diycode.R;
import com.xiseven.diycode.ui.fragment.BaseFragment;
import com.xiseven.diycode.ui.fragment.NewsFragment;
import com.xiseven.diycode.ui.fragment.ProjectFragment;
import com.xiseven.diycode.ui.fragment.SitesFragment;
import com.xiseven.diycode.ui.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    List<BaseFragment> fragments;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private Toolbar toolbar;
    private SearchView searchView;
    //选中的Fragment的对应的位置
    private int position = 0;
    //上次切换的Fragment
    private Fragment mContent;

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     */
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        toolbar = initToolbar("");
        toolbar.setLogo(R.mipmap.toolbar_icon);
        setBackEnable(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.iv_head).setOnClickListener(this);
        initFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new onBnvItemSelected());
        //设置默认显示
        switchFragment(mContent, fragments.get(position));
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new NewsFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new TopicFragment());
        fragments.add(new SitesFragment());
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 创建toolbar的菜单，初始化SearchView
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        // 配置SearchView的属性
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * toolbar菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_notification:
                break;
            case R.id.action_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    /**
     * 侧滑点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 底边栏点击事件
     */
    class onBnvItemSelected implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bnv_item_news:
                    position = 0;
                    break;
                case R.id.bnv_item_projects:
                    position = 1;
                    break;
                case R.id.bnv_item_topics:
                    position = 2;
                    break;
                case R.id.bnv_item_sites:
                    position = 3;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = fragments.get(position);
            //替换
            switchFragment(mContent, to);
            return true;
        }
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                ft.add(R.id.content_main, to);
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                ft.show(to);
            }
            ft.commit();
        }

    }


    /**
     * 头像的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        startActivity(new Intent(mActivity, LoginActivity.class));
        drawer.closeDrawers();
    }

    /**
     * SearchView提交回调
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e(TAG, "onQueryTextSubmit: " + query);
        return false;
    }

    /**
     * SearchView输入改变回调
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e(TAG, "onQueryTextChange: " + newText);
        return false;
    }
}

