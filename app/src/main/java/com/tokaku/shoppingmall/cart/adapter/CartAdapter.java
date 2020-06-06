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

public class CartAdapter extends RecyclerView.Adapter {
    private final List<GoodsBean> goodsBeanList;
    private Context mContext;

    public CartAdapter(Context mContext, List<GoodsBean> goodsBeanList) {
        this.mContext = mContext;
        this.goodsBeanList = goodsBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_cart,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartViewHolder cartViewHolder= (CartViewHolder) holder;
        cartViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }

    private class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView price;
        public CartViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);


        }

        public void setData(int position) {
            GoodsBean goodsBean = goodsBeanList.get(position);
            Glide.with(mContext).load(URL_IMG+goodsBean.getImageUrl()).into(image);
            name.setText(goodsBean.getName());
            price.setText(goodsBean.getPrice());
        }
    }
}
