package com.tokaku.shoppingmall;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.tokaku.shoppingmall.utils.urlText.CLOSE_STORE;
import static com.tokaku.shoppingmall.utils.urlText.COMIC_STORE;
import static com.tokaku.shoppingmall.utils.urlText.COSPLAY_STORE;
import static com.tokaku.shoppingmall.utils.urlText.FOOD_STORE;
import static com.tokaku.shoppingmall.utils.urlText.GAME_STORE;
import static com.tokaku.shoppingmall.utils.urlText.GUFENG_STORE;
import static com.tokaku.shoppingmall.utils.urlText.SHOUSHI_STORE;
import static com.tokaku.shoppingmall.utils.urlText.STICK_STORE;
import static com.tokaku.shoppingmall.utils.urlText.WENJU_STORE;

public class TagActivity extends Activity {

    private static final int CLOTH = 0;
    private static final int GAME = 1;
    private static final int COMIC = 2;
    private static final int COSPLAY = 3;
    private static final int ANTIQUITY = 4;
    private static final int STICK = 5;
    private static final int STATIONERY = 6;
    private static final int SNACKS = 7;
    private static final int JEWELRY = 8;
    private static final int MORE = 19;

    private RecyclerView rv_tagGood;
    private TextView tag_title;
    private ImageView back;

    private int position;
    private GoodsBeanData.ResultBean resultDate;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        Intent intent = getIntent();
        position = (int) intent.getSerializableExtra("position");

        findAllView();
        initDate();
        onClick();
    }

    private void onClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void findAllView() {
        rv_tagGood = findViewById(R.id.rv_tagGood);
        tag_title = findViewById(R.id.tag_title);
        back = findViewById(R.id.back);
    }

    /**
     * 使用OkHttps获取页面json
     */
    protected void initDate() {
        if (position < 9 && position >= 0) {

            switch (position) {
                case CLOTH:
                    url = CLOSE_STORE;
                    break;
                case GAME:
                    url = GAME_STORE;
                    break;
                case COMIC:
                    url = COMIC_STORE;
                    break;
                case COSPLAY:
                    url = COSPLAY_STORE;
                    break;
                case ANTIQUITY:
                    url = GUFENG_STORE;
                    break;
                case STICK:
                    url = STICK_STORE;
                    break;
                case STATIONERY:
                    url = WENJU_STORE;
                    break;
                case SNACKS:
                    url = FOOD_STORE;
                    break;
                case JEWELRY:
                    url = SHOUSHI_STORE;
                    break;
            }
            OkHttpUtils
                    .get()
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "读取数据出错: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
//                        Log.e(TAG, "读取数据成功: "+response);
                            Log.e(TAG, "读取数据成功");
                            processData(response);
                        }
                    });
        }else {
            Toast.makeText(this,"更多分类等待加入",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *解析json,并且创建适配器
     */
    private void processData(String json) {
        GoodsBeanData resultBeanData = JSON.parseObject(json, GoodsBeanData.class);
        resultDate = resultBeanData.getResult();
        if (resultDate != null) {
            Log.e(TAG, "解析成功");
            Log.e(TAG, "绑定适配器");
            //绑定适配器
            TagAdapter adapter = new TagAdapter(this, resultDate);
            rv_tagGood.setAdapter(adapter);
            rv_tagGood.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

            adapter.setOnGoodsItemView(new TagAdapter.OnGoodsItemView() {
                @Override
                public void onClick(int position) {
                    String cover_price = resultDate.getPage_data().get(position).getCover_price();
                    String name = resultDate.getPage_data().get(position).getName();
                    String figure = resultDate.getPage_data().get(position).getFigure();
                    String product_id = resultDate.getPage_data().get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(figure, cover_price,name, product_id);
                    startToGoodInfo(goodsBean);

                }
            });
        }
    }

    private void startToGoodInfo(GoodsBean goodsBean) {
        Intent intent = new Intent(this, GoodsInfoActivity.class);
        intent.putExtra("goodsBean", goodsBean);
        this.startActivity(intent);
    }


}