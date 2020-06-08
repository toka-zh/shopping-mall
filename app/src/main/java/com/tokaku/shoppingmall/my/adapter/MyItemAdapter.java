package com.tokaku.shoppingmall.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.GoodsInfoActivity;
import com.tokaku.shoppingmall.R;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class MyItemAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private List<GoodsBean> goodsBeans;

    public MyItemAdapter(Context mContext, List<GoodsBean> goodsBeans) {
        this.mContext = mContext;
        this.goodsBeans = goodsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StarItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StarItemViewHolder itemViewHolder = (StarItemViewHolder) holder;
        itemViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return Math.min(goodsBeans.size(), 10);
    }

    private class StarItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private LinearLayout my_item;

        public StarItemViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.my_image);
            name = view.findViewById(R.id.my_name);
            my_item = view.findViewById(R.id.my_item);
        }

        public void setData(final int position) {
            if (goodsBeans != null && goodsBeans.size() > 0) {
                GoodsBean goodsBean = goodsBeans.get(position);
                Glide.with(mContext).load(URL_IMG + goodsBean.getImageUrl()).into(image);
                name.setText(goodsBean.getName());
            }

            my_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startToGoodInfo(goodsBeans.get(position));
                }
            });
        }
    }


    private void startToGoodInfo(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra("goodsBean", goodsBean);
        mContext.startActivity(intent);
    }
}
