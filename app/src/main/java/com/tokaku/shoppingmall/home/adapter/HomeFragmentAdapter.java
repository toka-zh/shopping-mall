package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int PROMOTION = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;
    private Context mContext;
    private ResultBeanData.ResultBean resultDate;
    private LayoutInflater layoutInflater;

    private int currentType = BANNER;

    public HomeFragmentAdapter(FragmentActivity mContext, ResultBeanData.ResultBean resultDate) {
        this.mContext = mContext;
        this.resultDate = resultDate;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext,layoutInflater.inflate(R.layout.home_banner,null),resultDate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultDate.getBanner_info());
        }
    }

    @Override
    public long getItemId(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case PROMOTION:
                currentType = PROMOTION;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View view, ResultBeanData.ResultBean resultDate) {
            super(view);
            this.mContext = mContext;
            this.banner = view.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            List<String> imagesUri = new ArrayList<>();
        }
    }
}
