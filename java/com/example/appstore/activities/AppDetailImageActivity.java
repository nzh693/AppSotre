package com.example.appstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.appstore.R;

public class AppDetailImageActivity extends AppCompatActivity {

    private ImageView imageView_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail_image);
        imageView_exit=findViewById(R.id.detail_exit);
        imageView_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回上一个页面

            }
        });
    }
}
