package com.tokaku.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.Serializable;

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

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-06-03 15:34:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        back = (ImageButton)findViewById( R.id.back );
        more = (ImageButton)findViewById( R.id.more );
        image = (ImageView)findViewById( R.id.image );
        price = (TextView)findViewById( R.id.price );
        name = (TextView)findViewById( R.id.name );
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
            // Handle clicks for back
        } else if ( v == more ) {
            // Handle clicks for more
        } else if ( v == add ) {
            // Handle clicks for add
        } else if ( v == buy ) {
            // Handle clicks for buy
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        Intent intent = getIntent();
        GoodsBean goodsBean = (GoodsBean) intent.getSerializableExtra("goodsBean");

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
