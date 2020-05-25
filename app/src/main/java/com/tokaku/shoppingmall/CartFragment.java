package com.tokaku.shoppingmall;

import android.util.Log;
import android.view.View;

import com.tokaku.shoppingmall.base.BaseFragment;

public class CartFragment extends BaseFragment {

    private static final String TAG = CartFragment.class.getSimpleName();

    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.fragment_cart,null);
        Log.e(TAG, "initView: ");
        return view;
    }

    @Override
    protected void initDate() {
        super.initDate();
    }
}
