package com.tokaku.shoppingmall.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;
import com.tokaku.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView search;
    private ImageView scan;
    private ResultBeanData.ResultBean resultDate;
    private RecyclerView rvHome;
    private HomeFragmentAdapter adapter;

    @Override
    protected View initView() {
        Log.e(TAG, "initView: ");
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        rvHome = view.findViewById(R.id.recyclerview_home);
        search = view.findViewById(R.id.search);
        scan = view.findViewById(R.id.scan);

        initOnClick();
        return view;
    }

    private void initOnClick() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "search", Toast.LENGTH_SHORT).show();
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "scan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initDate() {
        super.initDate();

        String url = "http://10.0.2.2:8080/atguigu/json/HOME_URL.json";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "读取数据出错: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "读取数据成功: "+response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultDate = resultBeanData.getResult();
        Log.e(TAG, "解析成功");
        if (resultDate != null) {
            adapter = new HomeFragmentAdapter(mContext, resultDate);
            rvHome.setAdapter(adapter);
            rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
        }
    }

}
