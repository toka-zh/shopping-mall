package com.tokaku.shoppingmall.cart;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tokaku.shoppingmall.GoodsBean;
import com.tokaku.shoppingmall.MyApplication;
import com.tokaku.shoppingmall.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    private static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private Context context;
    SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context) {
        this.context = context;
        sparseArray = new SparseArray<>(100);
        listToSparseArray();
    }

    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    private void listToSparseArray() {
        List<GoodsBean> list = getAllData();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                GoodsBean goodsBean = list.get(i);
                sparseArray.put(Integer.parseInt(goodsBean.getId()), goodsBean);
            }
        }
    }

    private List<GoodsBean> sparseToArraytolist() {
        List<GoodsBean> list = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            list.add(goodsBean);
        }
        return list;
    }

    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        String json = CacheUtils.getString(context,JSON_CART);
        if (!TextUtils.isEmpty(json)) {
            goodsBeanList = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        return goodsBeanList;
    }

    public void addData(GoodsBean goodsBean) {
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getId()));
        if (tempData != null) {
            tempData.setGoods_num(tempData.getGoods_num() + 1);
        } else {
            tempData = goodsBean;
            tempData.setGoods_num(1);
        }
        sparseArray.put(Integer.parseInt(tempData.getId()),tempData);

        commit();
    }

    public void deleteData(GoodsBean goodsBean) {
        sparseArray.delete(Integer.parseInt(goodsBean.getId()));
        commit();
    }

    public void updateData(GoodsBean goodsBean){
        sparseArray.put(Integer.parseInt(goodsBean.getId()),goodsBean);
        commit();
    }

    private void commit() {
        List<GoodsBean> list = sparseToArraytolist();
        String json = new Gson().toJson(list);
        CacheUtils.saveString(context, JSON_CART, json);
    }

}
