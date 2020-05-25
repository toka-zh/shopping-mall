package com.tokaku.shoppingmall;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.tokaku.shoppingmall.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG, "initView: ");
        return null;
    }

    @Override
    protected void initDate() {
        super.initDate();
    }
}
