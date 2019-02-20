package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.triplebro.aran.sandw.activities.SelectAllActivity;
import com.triplebro.aran.sandw.adapters.MaybeAdapter;
import com.triplebro.aran.sandw.beans.TenCommodityInfo;
import com.triplebro.aran.sandw.managers.SearchManager;
import com.triplebro.aran.sandw.properties.AppProperties;
import com.triplebro.aran.sandw.views.InnerListView;

import java.util.List;

public class SearchHandler extends Handler {

    private Context context;
    private SearchManager searchManager;
    private RecyclerView rv_maybe_content;
    private InnerListView ilv_history_content;
    private InnerListView ilv_sale_content;

    public SearchHandler(Context context, RecyclerView rv_maybe_content, InnerListView ilv_history_content, InnerListView ilv_sale_content) {
        this.context = context;
        this.rv_maybe_content = rv_maybe_content;
        this.ilv_history_content = ilv_history_content;
        this.ilv_sale_content = ilv_sale_content;
    }

    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case AppProperties.SEARCH_FIND:
                String find = (String) msg.obj;
                if (searchManager != null) {
                    context.unbindService(searchManager);
                }
                Intent intent = new Intent(context, SelectAllActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("data", find);
                context.startActivity(intent);
                break;
            case AppProperties.SEARCH_GET_GOODS:
                List<TenCommodityInfo.TencommodityBean> data = (List<TenCommodityInfo.TencommodityBean>) msg.obj;
                if (searchManager != null) {
                    context.unbindService(searchManager);
                }
                MaybeAdapter maybeAdapter = new MaybeAdapter(context,data);
                rv_maybe_content.setAdapter(maybeAdapter);
                break;

        }
    }
}
