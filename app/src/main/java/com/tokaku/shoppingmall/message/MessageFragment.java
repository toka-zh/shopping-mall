package com.tokaku.shoppingmall.message;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.base.BaseFragment;
import com.tokaku.shoppingmall.message.adapter.MessageAdapter;

public class MessageFragment extends BaseFragment {
    private RecyclerView rv_message;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_message, null);
        rv_message = view.findViewById(R.id.rv_message);
        return null;
    }

    @Override
    protected void initDate() {
        super.initDate();
        MessageAdapter adapter = new MessageAdapter(mContext);
    }
}
