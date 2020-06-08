package com.tokaku.shoppingmall.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.cart.utils.CartStorage;
import com.tokaku.shoppingmall.my.utils.HistoryStorage;
import com.tokaku.shoppingmall.my.utils.StarStorage;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private static final int STAR = 0;
    private static final int HISTORY = 1;
    private int type = STAR;

    private LayoutInflater layoutInflater;

    private final Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mContext, layoutInflater.inflate(R.layout.my_item, null));
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case STAR:
                type = STAR;
                break;
            case HISTORY:
                type = HISTORY;
                break;
        }
        return type;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String itemName;
        List<GoodsBean> goodsBeans;
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (position == STAR) {
            itemName = "我的收藏";
            goodsBeans = StarStorage.getInstance().getAllData();
            myViewHolder.setData(goodsBeans, itemName);
        } else if (position == HISTORY) {
            itemName = "浏览历史";
            goodsBeans = HistoryStorage.getInstance().getAllData();
            myViewHolder.setData(goodsBeans, itemName);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView my_item_title;
        private RecyclerView my_item_rv;

        public MyViewHolder(Context mContext, View view) {
            super(view);
            my_item_title = view.findViewById(R.id.my_item_title);
            my_item_rv = view.findViewById(R.id.my_item_rv);
        }

        public void setData(List<GoodsBean> goodsBeans, String itemName) {
            my_item_title.setText(itemName);
            MyItemAdapter adapter = new MyItemAdapter(mContext, goodsBeans);
            my_item_rv.setAdapter(adapter);
            my_item_rv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
