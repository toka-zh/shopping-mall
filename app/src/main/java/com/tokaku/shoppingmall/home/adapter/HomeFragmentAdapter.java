package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int PROMOTION = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;
    private Context mContext;
    private ResultBeanData.ResultBean resultDate;
    private final LayoutInflater layoutInflater;

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
            return new BannerViewHolder(mContext, layoutInflater.inflate(R.layout.home_banner, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, layoutInflater.inflate(R.layout.home_channel, null));
        } else if (viewType == PROMOTION) {
            return new PromotionViewHolder(mContext, layoutInflater.inflate(R.layout.home_promotion, null));
        }
        return new BannerViewHolder(mContext, layoutInflater.inflate(R.layout.home_banner, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "绑定适配器" + position);
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultDate.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultDate.getChannel_info());
        } else if (getItemViewType(position) == PROMOTION) {
            PromotionViewHolder promotionViewHolder = (PromotionViewHolder) holder;
            promotionViewHolder.setData(resultDate.getAct_info());
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
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
        return 3;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        BannerViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            this.banner = view.findViewById(R.id.banner);
        }

        void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            final List<String> imagesUri = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                imagesUri.add(banner_info.get(i).getImage());
            }
            banner.setImages(imagesUri, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(mContext).load(URL_IMG + url).into(view);
                }
            });
        }
    }

    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gridView;
        private GridAdapter adapter;

        ChannelViewHolder(final Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            gridView = view.findViewById(R.id.grid);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {

            adapter = new GridAdapter(mContext, channel_info);
            gridView.setAdapter(adapter);
        }
    }

    private class PromotionViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager viewpager;
        private ViewPagerAdapter adapter;

        PromotionViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            viewpager = view.findViewById(R.id.viewpager);
        }

        void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            adapter = new ViewPagerAdapter(mContext, act_info);
            viewpager.setPageMargin(20);
            viewpager.setOffscreenPageLimit(3);
            viewpager.setPageTransformer(true
                    , new AlphaPageTransformer(
                            new ScaleInTransformer()));
            viewpager.setAdapter(adapter);
        }
    }
}
