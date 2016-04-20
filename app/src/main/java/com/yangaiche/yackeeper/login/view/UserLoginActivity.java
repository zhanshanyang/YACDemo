package com.yangaiche.yackeeper.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.MVPBaseActivity;
import com.yangaiche.yackeeper.login.presenter.UserLoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class UserLoginActivity extends MVPBaseActivity<IUserLoginView, UserLoginPresenter> implements IUserLoginView, View.OnClickListener {

    public static final String EXTRA_ON_ADDED_INTENT = UserLoginActivity.class.getName()+".on_added_intent";
    @Bind(R.id.ed_user_name)
    MaterialEditText ed_user_name;
    @Bind(R.id.ed_user_password)
    MaterialEditText ed_user_password;
    @Bind(R.id.btn_login_ok)
    Button btn_login_ok;
    @Bind(R.id.progress)
    MaterialProgressBar progress;

    public static Intent makeIntent(Context context, Intent onAddedIntent){
        Intent intent = new Intent(context, UserLoginActivity.class);
        intent.putExtra(UserLoginActivity.EXTRA_ON_ADDED_INTENT, onAddedIntent);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
        btn_login_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_login_ok){
            mPresenter.login(this, getUserName(), getUserPassword());
        }
    }

    @Override
    public String getUserName() {
        String name = ed_user_name.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            ed_user_name.setError("请输入用户名");
        }
        return name;
    }

    @Override
    public String getUserPassword() {
        String psd = ed_user_password.getText().toString().trim();
        if(TextUtils.isEmpty(psd)){
            ed_user_password.setError("请输入密码");
        }
        return psd;
    }

    @Override
    public void setUserName(String name) {
        ed_user_name.setText(name);
    }

    @Override
    public void setUserPasswpord(String psd) {
        ed_user_password.setText(psd);
    }

    @Override
    public void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        Intent onAddedIntent = getIntent().getParcelableExtra(UserLoginActivity.EXTRA_ON_ADDED_INTENT);
        if(onAddedIntent != null)
            try {
                startActivity(onAddedIntent);
            }catch (Exception e){
                e.printStackTrace();
            }
        finish();
    }

    @Override
    protected UserLoginPresenter createPresenter() {
        return new UserLoginPresenter();
    }
}
