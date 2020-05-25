package com.tokaku.shoppingmall;

import android.util.Log;
import android.view.View;

import com.tokaku.shoppingmall.base.BaseFragment;

public class MyFragment extends BaseFragment {

    private static final String TAG = MyFragment.class.getSimpleName();

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
