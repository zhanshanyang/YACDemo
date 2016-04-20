package com.yangaiche.yackeeper.orderCenter.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.MVPBaseActivity;
import com.yangaiche.yackeeper.bean.UserAccount;
import com.yangaiche.yackeeper.orderCenter.presenter.MainPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MVPBaseActivity<IMainView, MainPresenter>
        implements IMainView, NavigationView.OnNavigationItemSelectedListener , OrderCenterFragment.OnFragmentInteractionListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.navigation_view)
    NavigationView navigation_view;
    @Bind(R.id.container)
    FrameLayout frameLayout;

    LinearLayout headerLinearLayout;
    ImageView nav_imageView;
    TextView nav_name_tv;
    TextView nav_brief_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.judgeUserLogin(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initListener();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getUser2View(this);
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initView() {
        navigation_view.setNavigationItemSelectedListener(this);
        headerLinearLayout = (LinearLayout) navigation_view.getHeaderView(0);
        nav_imageView = ButterKnife.findById(headerLinearLayout, R.id.nav_imageView);
        nav_name_tv = ButterKnife.findById(headerLinearLayout, R.id.nav_name_tv);
        nav_brief_tv = ButterKnife.findById(headerLinearLayout, R.id.nav_brief_tv);
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle("养爱车管家");
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean show = super.onPrepareOptionsMenu(menu);
        if(!show)
            return show;
        MenuItem item = menu.findItem(R.id.action_settings);
        if(item != null)
            item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mPresenter.switch2Nav(id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void refreshUserView(UserAccount userAccount) {
        if(userAccount != null) {
            nav_imageView.setImageResource(R.drawable.default_user_icon);
            nav_name_tv.setText(userAccount.name);
            nav_brief_tv.setText(userAccount.phone);
        }
    }

    @Override
    public void switch2OrderCenter() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_order_center);
    }

    @Override
    public void switch2UserCenter() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_use_center);
    }

    @Override
    public void switch2Leave() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_leave);
    }

    @Override
    public void switch2Version() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_version_update);
    }

    @Override
    public void switch2Logout() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_logout);
    }

    @Override
    public void switch2Setting() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, OrderCenterFragment.newInstance("", "")).commit();
        toolbar.setTitle(R.string.action_settings);
    }
}
