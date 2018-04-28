package com.sisyphean.practice.utils;

import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

public class BottomNavigationViewHelper {

    public static void disableShiftMode(BottomNavigationView mView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mView.getChildAt(0);
        try {
            Field mShiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            mShiftingMode.setAccessible(true);
            mShiftingMode.setBoolean(menuView, false);
            mShiftingMode.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
