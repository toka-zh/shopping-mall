package com.tokaku.shoppingmall.my;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;

public class MyFragment extends BaseFragment {
    private ImageView headImage;
    private TextView myOrder;
    private TextView myStar;
    private TextView myHistory;
    private Button setting;



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my, null);
        headImage = view.findViewById(R.id.headimage);
        myOrder = view.findViewById(R.id.myorder);
        myStar = view.findViewById(R.id.mystar);
        myHistory = view.findViewById(R.id.myhistory);
        setting = view.findViewById(R.id.setting);
        return view;
    }

    @Override
    protected void initDate() {
        super.initDate();

        onClick();

    }

    private void onClick() {
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
