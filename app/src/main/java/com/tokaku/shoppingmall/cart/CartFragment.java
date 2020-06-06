package com.tokaku.shoppingmall.cart;

import android.util.Log;
import android.view.View;

import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;

import java.util.List;

public class CartFragment extends BaseFragment {

    private static final String TAG = CartFragment.class.getSimpleName();

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cart, null);
        Log.e(TAG, "购物车ui初始化");
        return view;
    }

    @Override
    protected void initDate() {
        super.initDate();
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        Log.e(TAG, "购物车数据初始化"+goodsBeanList.size());
        for (int i = 0; i < goodsBeanList.size(); i++) {
            Log.e(TAG, goodsBeanList.get(i).toString());
        }
    }
}
