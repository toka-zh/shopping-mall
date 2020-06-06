package com.tokaku.shoppingmall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tokaku.shoppingmall.cart.CartStorage;

import static com.tokaku.shoppingmall.utils.urlText.URL_IMG;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton back;
    private ImageButton more;
    private ImageView image;
    private TextView price;
    private TextView name;
    private WebView web;
    private ImageView service;
    private ImageView star;
    private Button add;
    private Button buy;
    private GoodsBean goodsBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-06-03 15:34:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        back = (ImageButton)findViewById( R.id.back );
        more = (ImageButton)findViewById( R.id.more );
        image = (ImageView)findViewById( R.id.item_cart_image);
        price = (TextView)findViewById( R.id.item_cart_price);
        name = (TextView)findViewById( R.id.item_cart_name);
        web = (WebView)findViewById( R.id.web );
        service = (ImageView)findViewById( R.id.service );
        star = (ImageView)findViewById( R.id.star );
        add = (Button)findViewById( R.id.add );
        buy = (Button)findViewById( R.id.buy );

        back.setOnClickListener( this );
        more.setOnClickListener( this );
        add.setOnClickListener( this );
        buy.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-06-03 15:34:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == back ) {
            // 返回
            finish();
        } else if ( v == more ) {
            // 更多
        } else if ( v == add ) {
            // 加入购物车按钮
            CartStorage.getInstance().addData(goodsBean);
            Toast.makeText(this,"加入购物车",Toast.LENGTH_SHORT).show();
        } else if ( v == buy ) {
            // 立即购买
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        Intent intent = getIntent();
        goodsBean = (GoodsBean) intent.getSerializableExtra("goodsBean");

        setData(goodsBean);
    }

    private void setData(GoodsBean goodsBean) {
        Glide.with(this).load(URL_IMG + goodsBean.getImageUrl()).into(image);
        price.setText(goodsBean.getPrice());
        name.setText(goodsBean.getName());
        web.loadUrl("https://www.baidu.com");
        WebSettings settings = web.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
