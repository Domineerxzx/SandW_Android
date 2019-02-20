package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.triplebro.aran.sandw.adapters.ShopBagAdapter;
import com.triplebro.aran.sandw.beans.ShopBagInfo;
import com.triplebro.aran.sandw.managers.ShopBagManager;
import com.triplebro.aran.sandw.properties.AppProperties;
import com.triplebro.aran.sandw.views.InnerListView;

import java.util.List;

public class ShopBagHandler extends Handler {

    private Context context;
    private ShopBagManager shopBagManager;
    private static InnerListView ilv_shop_content;
    private static RelativeLayout rl_shop_bag_empty;
    private static TextView tv_little_num;
    private static TextView tv_count_num;
    private ShopBagAdapter shopBagAdapter;
    private Double count;

    public ShopBagHandler(Context context) {
        this.context = context;
    }

    public ShopBagHandler(Context context, InnerListView ilv_shop_content, RelativeLayout rl_shop_bag_empty, TextView tv_little_num, TextView tv_count_num) {
        this.context = context;
        this.ilv_shop_content = ilv_shop_content;
        this.rl_shop_bag_empty = rl_shop_bag_empty;
        this.tv_little_num = tv_little_num;
        this.tv_count_num = tv_count_num;
    }


    public void setShopBagManager(ShopBagManager shopBagManager) {
        this.shopBagManager = shopBagManager;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case AppProperties.SHOP_BAG_EMPTY:
                rl_shop_bag_empty.setVisibility(View.VISIBLE);
                ilv_shop_content.setVisibility(View.GONE);
                tv_count_num.setText(String.valueOf(0.00));
                tv_little_num.setText(String.valueOf(0.00));
                context.unbindService(shopBagManager);
                break;
            case AppProperties.SHOP_BAG_SHOW:
                rl_shop_bag_empty.setVisibility(View.GONE);
                ilv_shop_content.setVisibility(View.VISIBLE);
                ShopBagInfo shopBagInfo = (ShopBagInfo) msg.obj;
                List<ShopBagInfo.ShoppingListBean> shoppingList = shopBagInfo.getShoppingList();
                count = 0.0;
                for (int i = 0; i <shoppingList.size(); i++) {
                    Double aDouble = new Double(shoppingList.get(i).getMoney());
                    count = count +aDouble;
                }
                tv_little_num.setText(String.valueOf(count));
                tv_count_num.setText(String.valueOf(count));
                shopBagAdapter = new ShopBagAdapter(context, shoppingList);
                ilv_shop_content.setAdapter(shopBagAdapter);
                context.unbindService(shopBagManager);
                break;
            case AppProperties.SHOP_BAG_DELETE:
                List<ShopBagInfo.ShoppingListBean> remove = (List<ShopBagInfo.ShoppingListBean>) msg.obj;
                if(remove.size() == 0){
                    rl_shop_bag_empty.setVisibility(View.VISIBLE);
                    ilv_shop_content.setVisibility(View.GONE);
                    tv_count_num.setText(String.valueOf(0.00));
                    tv_little_num.setText(String.valueOf(0.00));
                }
                count = 0.0;
                for (int i = 0; i <remove.size(); i++) {
                    Double aDouble = new Double(remove.get(i).getMoney());
                    count = count +aDouble;
                }
                tv_little_num.setText(String.valueOf(count));
                tv_count_num.setText(String.valueOf(count));
                shopBagAdapter = new ShopBagAdapter(context, remove);
                ilv_shop_content.setAdapter(shopBagAdapter);
                context.unbindService(shopBagManager);
                break;
            case AppProperties.SHOP_BAG_ADD:
                Toast.makeText(context, "添加购物袋成功", Toast.LENGTH_SHORT).show();
                context.unbindService(shopBagManager);
                break;
        }
    }
}
