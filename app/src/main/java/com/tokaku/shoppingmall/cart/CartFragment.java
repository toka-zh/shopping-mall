package com.tokaku.shoppingmall.cart;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView goodsCount;
    private TextView manage;
    private TextView finish;
    private Button settlement;
    private Button delete;
    private RelativeLayout buttonPanel;

    List<GoodsBean> goodsBeanList;

    private static final String TAG = CartFragment.class.getSimpleName();

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cart, null);
        recyclerView = view.findViewById(R.id.rv_cart);
        settlement = view.findViewById(R.id.settlement);
        delete = view.findViewById(R.id.delete);
        selectAll = view.findViewById(R.id.selectAll);
        priceAll = view.findViewById(R.id.priceAll);
        goodsCount = view.findViewById(R.id.goodsCount);
        manage = view.findViewById(R.id.manage);
        finish = view.findViewById(R.id.finish);
        buttonPanel = view.findViewById(R.id.buttonPanel);
        Log.e(TAG, "购物车ui初始化");
        return view;
    }

    @Override
    protected void initDate() {
        super.initDate();
        selectAll.setChecked(false);
        goodsBeanList = CartStorage.getInstance().getAllData();
        showData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initDate();
    }

    private void showData() {
        final CartAdapter adapter = new CartAdapter(mContext, goodsBeanList, selectAll, priceAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        setGoodsCount();
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manage.setVisibility(View.GONE);
                finish.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                buttonPanel.setVisibility(View.GONE);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manage.setVisibility(View.VISIBLE);
                finish.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                buttonPanel.setVisibility(View.VISIBLE);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"delete",Toast.LENGTH_SHORT).show();
                List<GoodsBean> selectedData = CartStorage.getInstance().getSelectedData();
                for (int i = 0; i < selectedData.size(); i++) {
                    if (selectedData.get(i).isSelected()){
                        CartStorage.getInstance().deleteData(goodsBeanList.get(i));
                    }

                    initDate();
                }
            }
        });


        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsBeanList != null) {
                    Intent intent = new Intent(mContext, Settlement.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mContext,"请先添加商品",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void setGoodsCount() {
        String s = "共" + goodsBeanList.size() + "件宝贝";
        goodsCount.setText(s);
    }
}
