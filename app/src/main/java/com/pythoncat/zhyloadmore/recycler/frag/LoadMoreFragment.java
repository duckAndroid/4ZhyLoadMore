package com.pythoncat.zhyloadmore.recycler.frag;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.zhyloadmore.R;
import com.pythoncat.zhyloadmore.recycler.adapter.LoadAdapter;
import com.pythoncat.zhyloadmore.recycler.domain.Bean;
import com.pythoncat.zhyloadmore.recycler.service.LoadApi;
import com.pythoncat.zhyloadmore.util.RxJavaUtil;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;

import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadMoreFragment extends Fragment {


    private SwipeRefreshLayout sw;
    private RecyclerView rv;
    private TextView tvLoading;
    private LoadMoreWrapper adapterWrapper;
    private ArrayList<Bean> datas;
    private Subscription refreshSubscript;
    private Subscription loadMoreSubscript;

    public LoadMoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load_more, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLoading = (TextView) view.findViewById(R.id.loading_view);
        tvLoading.setOnClickListener(v -> {
            Toast.makeText(getActivity().getApplicationContext(), "点击 texture", Toast.LENGTH_SHORT).show();
            refresh();
        });
        sw = (SwipeRefreshLayout) view.findViewById(R.id.load_more_sw);
        rv = (RecyclerView) view.findViewById(R.id.load_more_rv);
        sw.setOnRefreshListener(this::refresh);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        datas = new ArrayList<>();
        final LoadAdapter loadAdapter = new LoadAdapter(getActivity(), datas);
        adapterWrapper = new LoadMoreWrapper(loadAdapter);
        adapterWrapper.setLoadMoreView(R.layout.view_load_more);
        adapterWrapper.setOnLoadMoreListener(this::loadMore);
        rv.setAdapter(adapterWrapper);
        initShow();
        refresh();
    }

    private void loadMore() {
        RxJavaUtil.close(loadMoreSubscript);
        loadMoreSubscript = LoadApi.loadMoreApi()
                .subscribe(data -> {
                    if (data == null) {
                        showError();
                        return;
                    }
                    if (data.size() == 0) {
                        showEmpty();
                        return;
                    }
                    showData();
                    //  datas.clear();
                    datas.addAll(data);
                    LogUtils.e("loadMore   data.size = " + datas.size());
                    adapterWrapper.notifyDataSetChanged();
                }, e -> {
                    LogUtils.e(e);
                    showError();
                });
    }


    private void initShow() {
        showLoading();
    }

    private void showLoading() {
        tvLoading.setVisibility(View.VISIBLE);
        tvLoading.setText("Loading now !!!");
        sw.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
    }

    private void showEmpty() {
        tvLoading.setVisibility(View.VISIBLE);
        tvLoading.setText("Empty DATA");
        sw.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
    }

    private void showError() {
        tvLoading.setVisibility(View.VISIBLE);
        tvLoading.setText("error ###### ERROR");
        sw.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
    }

    private void showData() {
        LogUtils.e(" ## show recyclerview");
        tvLoading.setVisibility(View.GONE);
        sw.setVisibility(View.VISIBLE);
        rv.setVisibility(View.VISIBLE);
    }

    private void refresh() {
        LogUtils.e("on start....");
        RxJavaUtil.close(refreshSubscript);
        refreshSubscript = LoadApi.refreshApi()
                .subscribe(data -> {
                            if (data == null) {
                                showError();
                                return;
                            }
                            if (data.size() == 0) {
                                showEmpty();
                                return;
                            }
                            showData();
                            datas.clear();
                            datas.addAll(data);
                            LogUtils.e("refreshing   data.size = " + datas.size());
                            adapterWrapper.notifyDataSetChanged();
                        },
                        e -> {
                            LogUtils.e(e);
                            showError();
                        },
                        () -> sw.setRefreshing(false));
    }
}
