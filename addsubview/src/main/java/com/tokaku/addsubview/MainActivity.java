package com.tokaku.addsubview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AddSubView addsubview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addsubview= findViewById(R.id.addsubview);

        addsubview.setOnNumListener(new AddSubView.OnNumListener() {
            @Override
            public void OnNumClick(int value) {
                Toast.makeText(MainActivity.this,"num="+value,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
