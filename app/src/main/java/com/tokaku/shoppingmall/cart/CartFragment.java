package com.tokaku.shoppingmall.cart;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;
import com.tokaku.shoppingmall.cart.adapter.CartAdapter;

import java.util.List;

public class CartFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private CheckBox selectAll;
    private TextView priceAll;
    List<GoodsBean> goodsBeanList;

    private static final String TAG = CartFragment.class.getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cart, null);
        recyclerView = view.findViewById(R.id.rv_cart);
        selectAll = view.findViewById(R.id.selectAll);
        priceAll = view.findViewById(R.id.priceAll);
        Log.e(TAG, "购物车ui初始化");
        return view;
    }

    @Override
    protected void initDate() {
        super.initDate();
        goodsBeanList= CartStorage.getInstance().getAllData();
        showData();
    }

    private void showData() {
        CartAdapter adapter = new CartAdapter(mContext, goodsBeanList,selectAll,priceAll);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }
}
