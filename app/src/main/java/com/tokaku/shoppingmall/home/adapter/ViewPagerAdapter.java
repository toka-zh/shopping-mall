package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class ViewPagerAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ActInfoBean> resultDate;

    ViewPagerAdapter(Context mContext, List<ResultBeanData.ResultBean.ActInfoBean> resultDate) {
        this.mContext = mContext;
        this.resultDate = resultDate;
    }

    @Override
    public int getCount() {
        return resultDate.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext).load(URL_IMG + resultDate.get(position).getIcon_url()).into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
