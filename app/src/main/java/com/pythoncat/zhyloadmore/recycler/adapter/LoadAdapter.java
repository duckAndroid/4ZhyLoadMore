package com.pythoncat.zhyloadmore.recycler.adapter;

import android.content.Context;

import com.pythoncat.zhyloadmore.R;
import com.pythoncat.zhyloadmore.recycler.domain.Bean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by pythonCat on 2016/7/8.
 */
public class LoadAdapter extends CommonAdapter<Bean> {
    public LoadAdapter(Context context, List<Bean> datas) {
        super(context, R.layout.item_load_more, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Bean bean, int position) {
        holder.setText(R.id.item_tv1_qq, bean.roomId)
                .setText(R.id.item_tv2_desc, bean.roomName);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }
}
