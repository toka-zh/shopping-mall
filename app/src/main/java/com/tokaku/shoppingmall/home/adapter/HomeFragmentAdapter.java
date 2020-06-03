package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.GoodsInfoActivity;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int PROMOTION = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;

    private int currentType = BANNER;

    private Context mContext;
    private ResultBeanData.ResultBean resultDate;
    private LayoutInflater layoutInflater;
    private GoodsBean goodsBean;

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
        } else if (viewType == SECKILL) {
            return new SecKillViewHolder(mContext, layoutInflater.inflate(R.layout.home_seckill, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, layoutInflater.inflate(R.layout.home_recommend, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, layoutInflater.inflate(R.layout.home_hot, null));
        }
        return null;
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
        } else if (getItemViewType(position) == SECKILL) {
            SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
            secKillViewHolder.setData(resultDate.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultDate.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultDate.getHot_info());
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
        return 6;
    }

    private static class BannerViewHolder extends RecyclerView.ViewHolder {
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

    private static class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView cn_gr_channels;

        ChannelViewHolder(final Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            cn_gr_channels = view.findViewById(R.id.cn_gr_channels);

            cn_gr_channels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {

            ChannelGridItemAdapter adapter = new ChannelGridItemAdapter(mContext, channel_info);
            cn_gr_channels.setAdapter(adapter);
        }
    }

    private static class PromotionViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager viewpager;

        PromotionViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            viewpager = view.findViewById(R.id.viewpager);
        }

        void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(mContext, act_info);
            viewpager.setPageMargin(20);
            viewpager.setOffscreenPageLimit(3);
            viewpager.setPageTransformer(true
                    , new AlphaPageTransformer(
                            new ScaleInTransformer()));
            viewpager.setAdapter(adapter);
        }
    }

    private class SecKillViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final TextView sk_timer;
        private final TextView sk_more;
        private RecyclerView sk_rv_goods;
        int date;

        SecKillViewHolder(final Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            sk_timer = view.findViewById(R.id.sk_timer);
            sk_more = view.findViewById(R.id.sk_more);
            sk_rv_goods = view.findViewById(R.id.sk_rv_gods);
        }

        //        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                date -= 1000;
//                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//                sk_timer.setText(format.format(date));
//                handler.removeMessages(0);
//                handler.sendEmptyMessageDelayed(0, 1000);
//                if (date <= 0) {
//                    handler.removeMessages(0);
//                }
//
//            }
//        };
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NotNull Message msg) {
                date -= 1000;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
                sk_timer.setText(format.format(date));
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (date <= 0) {
                    handler.removeMessages(0);
                }
                return false;
            }
        });


        void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            date = Integer.parseInt(seckill_info.getEnd_time()) - Integer.parseInt(seckill_info.getStart_time());
            handler.sendEmptyMessageDelayed(0, 1000);

            sk_rv_goods.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            SecKillItemAdapter adapter = new SecKillItemAdapter(mContext, seckill_info);
            sk_rv_goods.setAdapter(adapter);

            adapter.setOnSeckillRecyclerView(new SecKillItemAdapter.OnSeckillRecyclerView() {
                @Override
                public void onClick(int position) {
                    String cover_price = "￥"+seckill_info.getList().get(position).getCover_price();
                    String name = seckill_info.getList().get(position).getName();
                    String figure = seckill_info.getList().get(position).getFigure();
                    String product_id = seckill_info.getList().get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(figure, cover_price,name, product_id);
                    startToGoodInfo(goodsBean);
                }
            });

        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final TextView rc_tv_more;
        private final GridView rc_gv_gods;

        RecommendViewHolder(final Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            rc_tv_more = view.findViewById(R.id.rc_tv_more);
            rc_gv_gods = view.findViewById(R.id.rc_gr_gods);

            rc_tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "查看更多", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendGridItemAdapter adapter = new RecommendGridItemAdapter(mContext, recommend_info);
            rc_gv_gods.setAdapter(adapter);
            rc_gv_gods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String cover_price = "￥"+recommend_info.get(position).getCover_price();
                    String name = recommend_info.get(position).getName();
                    String figure = recommend_info.get(position).getFigure();
                    String product_id = recommend_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(figure, cover_price,name, product_id);
                    startToGoodInfo(goodsBean);
                }
            });
        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView hot_tv_more;
        private GridView hot_gv_goods;

        public HotViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            hot_tv_more = view.findViewById(R.id.hot_tv_more);
            hot_gv_goods = view.findViewById(R.id.hot_gv_goods);

        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            HotGridItemAdapter adapter = new HotGridItemAdapter(mContext, hot_info);
            hot_gv_goods.setAdapter(adapter);
            hot_gv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String cover_price = "￥"+hot_info.get(position).getCover_price();
                    String name = hot_info.get(position).getName();
                    String figure = hot_info.get(position).getFigure();
                    String product_id = hot_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(figure, cover_price,name, product_id);
                    startToGoodInfo(goodsBean);
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
