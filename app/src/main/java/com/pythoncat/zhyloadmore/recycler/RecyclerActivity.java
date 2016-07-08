package com.pythoncat.zhyloadmore.recycler;

import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.zhyloadmore.R;
import com.pythoncat.zhyloadmore.base.BaseActivity;
import com.pythoncat.zhyloadmore.recycler.frag.LoadMoreFragment;

public class RecyclerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, new LoadMoreFragment())
                .commit();
        LogUtils.e("RecyclerActivity。oncreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("RecyclerActivity。onDestroy");
    }
}
