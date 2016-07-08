package com.pythoncat.zhyloadmore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pythoncat.zhyloadmore.recycler.RecyclerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_tv1)
                .setOnClickListener(v -> go2ZhyLoadMore());
    }

    private void go2ZhyLoadMore() {

//        LogUtils.e("点击 进入 load more UI");
        startActivity(new Intent(this, RecyclerActivity.class));
//        LogUtils.e("点击 进入 load more UI  ############### end");
    }
}
