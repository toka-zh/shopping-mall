package com.tokaku.shoppingmall.my;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;
import com.tokaku.shoppingmall.my.adapter.MyAdapter;

public class MyFragment extends BaseFragment {
    private ImageView headImage;
    private TextView myOrder;
    private TextView myStar;
    private TextView myHistory;
    private Button setting;
    private RecyclerView rvMy;



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my, null);
        rvMy = view.findViewById(R.id.rv_my);
        headImage = view.findViewById(R.id.headimage);
        setting = view.findViewById(R.id.setting);

        onClick();
        return view;
    }

    private void onClick() {

        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"更换头像",Toast.LENGTH_SHORT).show();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"设置功能尚未开放",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initDate() {
        super.initDate();
        MyAdapter adapter = new MyAdapter(mContext);
        rvMy.setAdapter(adapter);
        rvMy.setLayoutManager(new GridLayoutManager(mContext,1));


    }

    @Override
    public void onResume() {
        super.onResume();
        initDate();
    }
}
