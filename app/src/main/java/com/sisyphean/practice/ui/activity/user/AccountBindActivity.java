package com.sisyphean.practice.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.activity.BaseToolBarActivity;

public class AccountBindActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        Spinner spinner = findViewById(R.id.spinner);
        String[] mItems = {"java", "php", "python", "C++"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText editText = findViewById(R.id.et_account);
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void onMenuItemClickListener(MenuItem item) {

    }

    @Override
    protected int setToolbarTitle() {
        return R.string.title_bind_account;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_account;
    }

    @Override
    protected void createPresenter() {

    }
}
