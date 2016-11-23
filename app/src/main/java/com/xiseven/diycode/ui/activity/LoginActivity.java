package com.xiseven.diycode.ui.activity;

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
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.xiseven.diycode.R;
import com.xiseven.diycode.utils.ApiUtils;

import butterknife.BindView;


public class LoginActivity extends BaseActivity {
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
    @Override
    public int getContentViewId() {

        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        Toolbar toolbar = initToolbar("登录");
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
        ApiUtils.getToken("xiseven", "lq19960717001x");

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
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

}
