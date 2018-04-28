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
                FragmentTransaction ft = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        ft.replace(R.id.container, mFragments.get(0));
                        ft.commitAllowingStateLoss();
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        ft.replace(R.id.container, mFragments.get(1));
                        ft.commitAllowingStateLoss();
                        break;
                    case R.id.tab_navigation:
                        ft.replace(R.id.container, mFragments.get(2));
                        ft.commitAllowingStateLoss();
                        break;
                    case R.id.tab_project:
                        ft.replace(R.id.container, mFragments.get(3));
                        ft.commitAllowingStateLoss();
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
}
