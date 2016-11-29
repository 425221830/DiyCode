package com.xiseven.diycode.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.ui.presenter.BasePresenter;
import com.xiseven.diycode.ui.presenter.LoginPresenter;
import com.xiseven.diycode.ui.iView.ILoginView;

import butterknife.BindView;


public class LoginActivity extends BaseActivity implements ILoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.accountsTextInput)
    TextInputLayout accountsTextInput;
    @BindView(R.id.passwordTextInput)
    TextInputLayout passwordTextInput;
    @BindView(R.id.et_password)
    EditText mPasswordView;
    @BindView(R.id.et_accounts)
    EditText mAccountsView;
    @BindView(R.id.sign_in_button)
    Button sign_in_button;
    @BindView(R.id.activity_login)
    FrameLayout loginLayout;
    private LoginPresenter mPresenter;
    private ProgressDialog progressDialog;

    @Override
    public int getContentViewId() {

        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        Toolbar toolbar = initToolbar("登录");
        mPresenter = new LoginPresenter(this);
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("登录中...");
        //设置滑动返回
        setBackEnable(true);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    Log.e("TAG", "onEditorAction: 11");
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }


    private void attemptLogin() {
        Log.d("tag", "attemptLogin: ");
        accountsTextInput.setErrorEnabled(false);
        passwordTextInput.setErrorEnabled(false);
        String accounts = mAccountsView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (TextUtils.isEmpty(accounts)) {
            accountsTextInput.setError("请输入邮箱/用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordTextInput.setError("请输入密码");
            return;
        } else if (!isPasswordValid(password)) {
            passwordTextInput.setError("密码过短");
            return;
        }
        progressDialog.show();
        mPresenter.login(accounts, password);
    }

    //简单判断
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


    @Override
    public void loginSuccess() {
        Log.d(TAG, "loginSuccess: ");
        progressDialog.dismiss();
        showToast("登录成功");
        finish();
    }

    @Override
    public void loginFailed() {
        Log.d(TAG, "loginFailed: ");
        progressDialog.dismiss();
        showToast("登录失败");
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (LoginPresenter) presenter;
    }
}
