package com.tokaku.shoppingmall;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tokaku.shoppingmall.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView search;
    private ImageView scan;

    @Override
    protected View initView() {
        Log.e(TAG, "initView: ");
        View view = View.inflate(mContext,R.layout.fragment_home,null);
        search = view.findViewById(R.id.search);
        scan = view.findViewById(R.id.scan);

        initOnClick();
        return view;
    }

    private void initOnClick() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "search", Toast.LENGTH_SHORT).show();
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "scan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initDate() {
        super.initDate();
    }
}
