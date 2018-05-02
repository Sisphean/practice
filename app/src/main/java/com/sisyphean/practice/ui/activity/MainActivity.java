package com.sisyphean.practice.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.sisyphean.practice.R;
import com.sisyphean.practice.ui.fragment.HomeFragment;
import com.sisyphean.practice.ui.fragment.KnowledgeSysFragment;
import com.sisyphean.practice.ui.fragment.NavigationFragment;
import com.sisyphean.practice.ui.fragment.ProjectFragment;
import com.sisyphean.practice.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewGroup container;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private List<Fragment> mFragments;
    private int mLastFragmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        container = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.navigation_btn);
        fragmentManager = getFragmentManager();

        initFragments();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        switchFragment(0);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        switchFragment(1);
                        break;
                    case R.id.tab_navigation:
                        switchFragment(2);
                        break;
                    case R.id.tab_project:
                        switchFragment(3);
                        break;
                }
                return true;
            }
        });
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        Fragment homeFragment = HomeFragment.getInstance();
        Fragment knowLedgeSysFragment = KnowledgeSysFragment.getInstance();
        Fragment navigationFragment = NavigationFragment.getInstance();
        Fragment projectFragment = ProjectFragment.getInstance();
        mFragments.add(homeFragment);
        mFragments.add(knowLedgeSysFragment);
        mFragments.add(navigationFragment);
        mFragments.add(projectFragment);
    }

    private void switchFragment(int position) {
        Fragment targetFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(mLastFragmentId);
        mLastFragmentId = position;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            ft.remove(targetFragment);
            ft.add(R.id.container, targetFragment);
        }

        ft.show(targetFragment);
        ft.commitAllowingStateLoss();

    }
}