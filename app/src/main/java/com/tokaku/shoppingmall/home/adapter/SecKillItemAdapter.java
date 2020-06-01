package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

class SecKillItemAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;

    SecKillItemAdapter(Context mContext, ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.list = seckill_info.getList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SecKillItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_seckill, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SecKillItemViewHolder secKillItemViewHolder = (SecKillItemViewHolder) holder;
        secKillItemViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    private class SecKillItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textView2;
        private ImageView imageView;
        private LinearLayout linearLayout;

        public SecKillItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.sk_timer);
            textView2 = view.findViewById(R.id.sk_more);
            imageView = view.findViewById(R.id.imageView);
            linearLayout = view.findViewById(R.id.linearLayout);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"秒杀"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(final int position) {
            ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
            textView.setText("￥" + listBean.getCover_price());
            textView2.setText("￥" + listBean.getOrigin_price());
            Glide.with(mContext).load(URL_IMG + listBean.getFigure()).into(imageView);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnSeckillItem{
        void onItemClick(int position);
    }

    public OnSeckillItem seckillItem;

    public void setSeckillItem(OnSeckillItem seckillItem) {
        this.seckillItem = seckillItem;
    }
}
