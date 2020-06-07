package com.tokaku.shoppingmall;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.tokaku.shoppingmall.base.BaseFragment;
import com.tokaku.shoppingmall.cart.fragment.CartFragment;
import com.tokaku.shoppingmall.home.fragment.HomeFragment;
import com.tokaku.shoppingmall.message.MessageFragment;
import com.tokaku.shoppingmall.my.MyFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
    }

    private void initView() {
        RadioGroup radioGroup = findViewById(R.id.rg_title);
        radioGroup.check(R.id.rb_home);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_Fragment, fragments.get(0)).commit();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new CartFragment());
        fragments.add(new MyFragment());
    }

    public void clickFragment(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                position = 0;
                break;
            case R.id.rb_message:
                position = 1;
                break;
            case R.id.rb_cart:
                position = 2;
                break;
            case R.id.rb_my:
                position = 3;
                break;
        }

        BaseFragment to = fragments.get(position);
        switchFragment(mContext, to);
    }

    private void switchFragment(Fragment from, BaseFragment to) {
        if (mContext != to) {
            mContext = to;
            if (to != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!to.isAdded()) {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    transaction.add(R.id.frame_Fragment, to).commit();
                } else {
                    if (from != null) {
                        transaction.hide(from);
                    }
                    transaction.show(to).commit();
                }
            }
        }
    }
}
