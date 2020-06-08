package com.tokaku.shoppingmall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;


public class TagAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<GoodsBeanData.ResultBean.PageDataBean> goodsData;

    public TagAdapter(Context context, GoodsBeanData.ResultBean resultBean) {
        this.context = context;
        this.goodsData = resultBean.getPage_data();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GoodsListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_goodslist,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GoodsListViewHolder viewHolder = (GoodsListViewHolder) holder;
        viewHolder.setData(goodsData,position);
    }

    @Override
    public int getItemCount() {
        return goodsData.size();
    }

    private class GoodsListViewHolder extends RecyclerView.ViewHolder {
        private ImageView gl_image;
        private TextView gl_name;
        private TextView gl_price;
        private LinearLayout item;
        public GoodsListViewHolder(View view) {
            super(view);
            gl_image = view.findViewById(R.id.gl_image);
            gl_name = view.findViewById(R.id.gl_name);
            gl_price = view.findViewById(R.id.gl_price);
            item = view.findViewById(R.id.goodsitem);

        }

        public void setData(List<GoodsBeanData.ResultBean.PageDataBean> goodsData, final int position) {
            GoodsBeanData.ResultBean.PageDataBean oneGoods = goodsData.get(position);
            Glide.with(context).load(URL_IMG+oneGoods.getFigure()).into(gl_image);
            gl_name.setText(oneGoods.getName());
            gl_price.setText(oneGoods.getCover_price());

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onGoodsItemView != null) {
                        onGoodsItemView.onClick(position);
                    }
                }
            });
        }
    }


    public interface OnGoodsItemView {
        void onClick(int position);
    }

    private TagAdapter.OnGoodsItemView onGoodsItemView;

    public void setOnGoodsItemView(OnGoodsItemView onGoodsItemView) {
        this.onGoodsItemView = onGoodsItemView;
    }

}
