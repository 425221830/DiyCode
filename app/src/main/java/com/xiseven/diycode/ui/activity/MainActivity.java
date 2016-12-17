package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.MessageEvent;
import com.xiseven.diycode.ui.fragment.BaseFragment;
import com.xiseven.diycode.ui.fragment.NewsFragment;
import com.xiseven.diycode.ui.fragment.ProjectFragment;
import com.xiseven.diycode.ui.fragment.SitesFragment;
import com.xiseven.diycode.ui.fragment.TopicFragment;
import com.xiseven.diycode.ui.iView.IMainView;
import com.xiseven.diycode.ui.presenter.BasePresenter;
import com.xiseven.diycode.ui.presenter.MainPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, SearchView.OnQueryTextListener, IMainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    List<BaseFragment> fragments;
    @BindView(R.id.content_main)
    View content_main;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    CircleImageView iv_head;
    TextView tv_accounts;

    public Toolbar toolbar;
    private SearchView searchView;
    //选中的Fragment的对应的位置
    private int position = 0;
    //上次切换的Fragment
    private Fragment mContent;
    private MainPresenter mPresenter;
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabNews;
    private FloatingActionButton fabProject;
    private FloatingActionButton fabTopic;
    private long l = 0;

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
    protected void initAllMembers(Bundle savedInstanceState) {
        toolbar = initToolbar("");
        toolbar.setLogo(R.mipmap.toolbar_icon);
        setBackEnable(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        iv_head = (de.hdodenhof.circleimageview.CircleImageView) headerView.findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        tv_accounts = (TextView) headerView.findViewById(R.id.tv_username);
        bottomNavigationView.setOnNavigationItemSelectedListener(new onBnvItemSelect());
        initFragment();
        initFab();
        //设置默认显示
        switchFragment(mContent, fragments.get(position));
        mPresenter = new MainPresenter(this);
        if (mPresenter.isLogin()) {
            mPresenter.updateLogin();
        }
        EventBus.getDefault().register(this);
    }

    private void initFab() {
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        fabNews = (FloatingActionButton) findViewById(R.id.fab_create_news);
        fabProject = (FloatingActionButton) findViewById(R.id.fab_create_project);
        fabTopic = (FloatingActionButton) findViewById(R.id.fab_create_topic);
        fabNews.setOnClickListener(this);
        fabProject.setOnClickListener(this);
        fabTopic.setOnClickListener(this);
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
        } else if (fabMenu.isExpanded()) {
            fabMenu.collapse();
        } else {
            //连续两次返回退出
            if (System.currentTimeMillis() - l < 2000) {
                super.onBackPressed();
            } else {
                l = System.currentTimeMillis();
                showToast("再按一次返回键退出");
            }

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
        fabMenu.collapse();
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

    /**
     * 侧滑点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        fabMenu.collapse();
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_mytopic:
                startActivity(MyTopicActivity.class);
                break;
            case R.id.nav_myfavorite:
                startActivity(MyFavoriteActivity.class);
                break;
            case R.id.nav_myreplies:
                startActivity(MyRepliesActivity.class);
                break;
            case R.id.nav_myfollow:
                startActivity(MyFollowActivity.class);
                break;
            case R.id.nav_settings:
                startActivity(SettingsActivity.class);
                break;
            case R.id.nav_about:
                startActivity(AboutActivity.class);
                break;

        }
        drawer.closeDrawers();
        return true;
    }

    /**
     * 头像的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        fabMenu.collapse();
        drawer.closeDrawers();
        switch (view.getId()) {
            case R.id.iv_head:
                if (mPresenter.isLogin()) {
                    startActivity(MyInfoActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.fab_create_news:
                Toast.makeText(this, "news", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_create_project:
                Toast.makeText(this, "project", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_create_topic:
                Toast.makeText(this, "topic", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 底边栏点击事件
     */
    class onBnvItemSelect implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fabMenu.collapse();
            Log.e(TAG, "onNavigationItemSelected: click");
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
                ft.add(R.id.content_main_Fragment, to);
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
     * eventbus消息处理登录和注销后的显示操作
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent event) {
        if (event.getWhat() == 1) {
            mPresenter.showHeader();
        } else {
            iv_head.setImageResource(R.mipmap.icon);
            tv_accounts.setText("");
        }
    }

    /**
     * 登录成功后会执行该方法显示用户头像和名称
     *
     * @param headImg
     * @param userName
     */
    @Override
    public void showHeader(Bitmap headImg, String userName) {
        iv_head.setImageBitmap(headImg);
        tv_accounts.setText(userName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void startActivity(Class c) {
        startActivity(new Intent(mActivity, c));
    }
}

