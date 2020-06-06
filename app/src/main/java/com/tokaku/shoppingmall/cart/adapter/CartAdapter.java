package com.tokaku.shoppingmall.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.cart.AddSubView;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class CartAdapter extends RecyclerView.Adapter {
    private final CheckBox selectAll;
    private final TextView priceAll;

    private final List<GoodsBean> goodsBeanList;
    private Context mContext;

    public CartAdapter(Context mContext, List<GoodsBean> goodsBeanList, CheckBox selectAll, TextView priceAll) {
        this.mContext = mContext;
        this.goodsBeanList = goodsBeanList;
        this.selectAll = selectAll;
        this.priceAll = priceAll;

//        getTotalPrice();
    }

    private void getTotalPrice() {
        double sum = 0;
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            if (goodsBean.isSelected()){
                sum+= (double) goodsBean.getGoods_num() * Double.parseDouble(goodsBean.getPrice());
            }
        }
        sum +=100;
        priceAll.setText(""+sum);
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
        private CheckBox select;
        private ImageView image;
        private TextView name;
        private TextView price;
        private AddSubView addSub;
        public CartViewHolder(View view) {
            super(view);
            select = view.findViewById(R.id.item_cart_select);
            image = view.findViewById(R.id.item_cart_image);
            name = view.findViewById(R.id.item_cart_name);
            price = view.findViewById(R.id.item_cart_price);
            addSub = view.findViewById(R.id.item_cart_addSub);
        }

        public void setData(int position) {
            GoodsBean goodsBean = goodsBeanList.get(position);
            Glide.with(mContext).load(URL_IMG+goodsBean.getImageUrl()).into(image);
            name.setText(goodsBean.getName());
            price.setText(goodsBean.getPrice());
        }
    }
}
