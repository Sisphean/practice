package com.sisyphean.practice.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sisyphean.practice.R;
import com.sisyphean.practice.presenter.UserMainPagePresenter;
import com.sisyphean.practice.ui.activity.logon.ResetPwdActivity;
import com.sisyphean.practice.ui.activity.user.AuthActivity;
import com.sisyphean.practice.ui.activity.user.OrderDetailActivity;
import com.sisyphean.practice.ui.activity.user.PromoteActivity;
import com.sisyphean.practice.ui.activity.user.RechargeActivity;
import com.sisyphean.practice.ui.activity.user.WithdrawAccountActivity;
import com.sisyphean.practice.ui.activity.user.WithdrawActivity;
import com.sisyphean.practice.ui.fragment.BaseFragment;
import com.sisyphean.practice.view.user.IUserMainPageView;

public class UserMainPageFragment extends BaseFragment<UserMainPagePresenter> implements IUserMainPageView, View.OnClickListener {

    private TextView tv_simple_name;
    private TextView tv_name;
    private TextView tv_ustd;

    public static Fragment getInstance() {
        UserMainPageFragment userMainPageFragment = new UserMainPageFragment();
        return userMainPageFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getUserInfo();
    }

    @Override
    protected void initView(View rootView) {
        ViewGroup rl_recharge = rootView.findViewById(R.id.rl_recharge);
        ViewGroup rl_withdraw = rootView.findViewById(R.id.rl_withdraw);
        ViewGroup rl_auth = rootView.findViewById(R.id.rl_auth);
        ViewGroup rl_trade_log = rootView.findViewById(R.id.rl_trade_log);
        ViewGroup rl_recharge_log = rootView.findViewById(R.id.rl_recharge_log);
        ViewGroup rl_withdraw_log = rootView.findViewById(R.id.rl_withdraw_log);
        ViewGroup rl_withdraw_account = rootView.findViewById(R.id.rl_withdraw_account);
        ViewGroup rl_my_trade = rootView.findViewById(R.id.rl_my_trade);
        ViewGroup rl_promote = rootView.findViewById(R.id.rl_promote);
        ViewGroup rl_reset_pwd = rootView.findViewById(R.id.rl_reset_pwd);
        ViewGroup rl_support = rootView.findViewById(R.id.rl_support);

        rl_recharge.setOnClickListener(this);
        rl_withdraw.setOnClickListener(this);
        rl_auth.setOnClickListener(this);
        rl_trade_log.setOnClickListener(this);
        rl_recharge_log.setOnClickListener(this);
        rl_withdraw_log.setOnClickListener(this);
        rl_withdraw_account.setOnClickListener(this);
        rl_my_trade.setOnClickListener(this);
        rl_promote.setOnClickListener(this);
        rl_reset_pwd.setOnClickListener(this);
        rl_support.setOnClickListener(this);

        tv_simple_name = rootView.findViewById(R.id.tv_simple_name);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_ustd = rootView.findViewById(R.id.tv_ustd);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_mainpage;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new UserMainPagePresenter();
    }


    @Override
    public void toRechargeActivity() {
        Intent intent = new Intent(getActivity(), RechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void toWithdrawActivity() {
        Intent intent = new Intent(getActivity(), WithdrawActivity.class);
        startActivity(intent);
    }

    @Override
    public void toAuthActivity() {
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void toRechargeLogActivity() {

    }

    @Override
    public void toWithdrawLogActivity() {

    }

    @Override
    public void toTradeLogActivity() {

    }

    @Override
    public void toWithdrawAccountActivity() {
        Intent intent = new Intent(getActivity(), WithdrawAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void toMyTradeActivity() {

    }

    @Override
    public void toPromoteActivity() {
        Intent intent = new Intent(getActivity(), PromoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void toResetPwdActivity() {
        Intent intent = new Intent(getActivity(), ResetPwdActivity.class);
        startActivity(intent);
    }

    @Override
    public void toSupportActivity() {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setName(String name) {
        if (!TextUtils.isEmpty(name) && name.length() > 0) {
            char firstChar = name.charAt(0);
            tv_simple_name.setText(String.valueOf(firstChar));
            tv_name.setText(name);
        }
    }

    @Override
    public void setUstd(float ustd) {
        tv_ustd.setText(String.valueOf(ustd));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case  R.id.rl_recharge:
                toRechargeActivity();
                break;
            case R.id.rl_withdraw:
                toWithdrawActivity();
                break;
            case R.id.rl_auth:
                toAuthActivity();
                break;
            case R.id.rl_trade_log:
                toTradeLogActivity();
                break;
            case R.id.rl_recharge_log:
                toRechargeLogActivity();
                break;
            case R.id.rl_withdraw_log:
                toWithdrawLogActivity();
                break;
            case R.id.rl_withdraw_account:
                toWithdrawAccountActivity();
                break;
            case R.id.rl_my_trade:
                toMyTradeActivity();
                break;
            case R.id.rl_promote:
                toPromoteActivity();
                break;
            case R.id.rl_reset_pwd:
                toResetPwdActivity();
                break;
            case R.id.rl_support:
                toSupportActivity();
                break;
        }
    }
}
