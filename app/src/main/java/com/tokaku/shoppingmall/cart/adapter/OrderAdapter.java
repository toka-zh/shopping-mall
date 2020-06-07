package com.tokaku.shoppingmall.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.R;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class OrderAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<GoodsBean> beanList;

    public OrderAdapter(Context mContext, List<GoodsBean> beanList) {
        this.mContext = mContext;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_settlement,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        orderViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    private class OrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView count;
        private TextView price;
        public OrderViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_settlement_image);
            name = view.findViewById(R.id.item_settlement_name);
            count = view.findViewById(R.id.item_settlement_count);
            price = view.findViewById(R.id.item_settlement_price);
        }

        public void setData(int position) {
            GoodsBean goodsBean = beanList.get(position);
            Glide.with(mContext).load(URL_IMG+goodsBean.getImageUrl()).into(imageView);
            name.setText(goodsBean.getName());
            String s_count = "x" + goodsBean.getGoods_num();
            count.setText(s_count);
            price.setText(goodsBean.getPrice());
        }
    }
}
