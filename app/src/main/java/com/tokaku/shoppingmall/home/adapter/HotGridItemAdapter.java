package com.tokaku.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.home.bean.ResultBeanData;

import java.util.List;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class HotGridItemAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.HotInfoBean> resultDate;

    public HotGridItemAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> resultDate) {
        this.mContext = mContext;
        this.resultDate = resultDate;
    }

    @Override
    public int getCount() {
        return resultDate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_home_hot,null);
            holder = new ViewHolder();
            holder.hot_gr_image = convertView.findViewById(R.id.hot_gr_image);
            holder.hot_gr_name = convertView.findViewById(R.id.hot_gr_name);
            holder.hot_gr_price = convertView.findViewById(R.id.hot_gr_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = resultDate.get(position);
        Glide.with(mContext).load(URL_IMG+hotInfoBean.getFigure()).into(holder.hot_gr_image);
        holder.hot_gr_name.setText(hotInfoBean.getName());
        holder.hot_gr_price.setText(hotInfoBean.getCover_price());

        return convertView;
    }

    private static class ViewHolder{
        private ImageView hot_gr_image;
        private TextView hot_gr_name;
        private TextView hot_gr_price;
    }
}
