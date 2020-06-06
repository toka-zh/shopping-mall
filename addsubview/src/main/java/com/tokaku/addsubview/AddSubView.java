package com.tokaku.addsubview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private TextView num;
    private ImageView add;
    private ImageView sub;

    private int value = 1;
    private int maxValue = 99;
    private int minValue = 1;
    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context,R.layout.addsubview,this);
        num = findViewById(R.id.num);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);

        int value = getValue();
        setValue(value);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                if (value < maxValue) {
                    value++;
                }
                break;
            case R.id.sub:
                if (value > minValue) {
                    value--;
                }
                break;
        }
        if (onNumListener!=null){
            onNumListener.OnNumClick(value);
        }

        setValue(value);
    }

    public int getValue() {
        String s = num.getText().toString();
        if (!s.isEmpty()) {
            value = Integer.parseInt(s);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        num.setText(value+"");
    }

    public interface OnNumListener{
        void OnNumClick(int value);
    }

    private OnNumListener onNumListener;

    public void setOnNumListener(OnNumListener onNumListener) {
        this.onNumListener = onNumListener;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
