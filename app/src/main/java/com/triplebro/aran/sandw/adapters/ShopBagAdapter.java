package com.triplebro.aran.sandw.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.beans.ShopBagInfo;
import com.triplebro.aran.sandw.cache.ImageCacheOP;
import com.triplebro.aran.sandw.handlers.ImageHandler;
import com.triplebro.aran.sandw.handlers.ShopBagHandler;
import com.triplebro.aran.sandw.managers.ShopBagManager;
import com.triplebro.aran.sandw.views.InnerListView;
import com.triplebro.aran.sandw.views.TwoButtonDialog;

import java.io.File;
import java.util.List;

public class ShopBagAdapter extends BaseAdapter {

    private Context context;
    private List<ShopBagInfo.ShoppingListBean> shoppingListBeans;
    private ImageCacheOP imageCacheOP;

    public ShopBagAdapter(Context context, List<ShopBagInfo.ShoppingListBean> shoppingListBeans) {
        this.context = context;
        this.shoppingListBeans = shoppingListBeans;
    }

    @Override
    public int getCount() {
        return shoppingListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ShopBagAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ShopBagAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_shop_bag, null);
            viewHolder.ll_size = convertView.findViewById(R.id.ll_size);
            viewHolder.ll_count = convertView.findViewById(R.id.ll_count);
            viewHolder.iv_delete_goods = convertView.findViewById(R.id.iv_delete_goods);
            viewHolder.tv_coast = convertView.findViewById(R.id.tv_coast);
            viewHolder.tv_size_title = convertView.findViewById(R.id.tv_size_title);
            viewHolder.tv_count_title = convertView.findViewById(R.id.tv_count_title);
            viewHolder.tv_size = convertView.findViewById(R.id.tv_size);
            viewHolder.tv_count = convertView.findViewById(R.id.tv_count);
            viewHolder.tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
            viewHolder.iv_goods = convertView.findViewById(R.id.iv_goods);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShopBagAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.iv_delete_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
                twoButtonDialog.show("移除商品", "确定要将该商品移出购物袋吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ShopBagHandler shopBagHandler = new ShopBagHandler(context);
                        String session = context.getSharedPreferences("session", Context.MODE_PRIVATE).getString("session", null);
                        int commodityId = shoppingListBeans.get(position).getCommodityId();
                        String size = shoppingListBeans.get(position).getSize();
                        if (shoppingListBeans.size() > 0) {
                            shoppingListBeans.remove(position);
                        }
                        ShopBagManager shopBagManager = new ShopBagManager(context, shopBagHandler, session, commodityId, size, shoppingListBeans);
                        shopBagManager.deleteShopBag();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "取消移除", Toast.LENGTH_SHORT).show();
                    }
                }, ((Activity) context).getFragmentManager());
            }
        });
        String fileName = context.getCacheDir() + "/" + shoppingListBeans.
                get(position).getCommodityId() + shoppingListBeans.get(position).getSize() + ".png";
        ImageHandler imageHandler = new ImageHandler(context, viewHolder.iv_goods, fileName);
        imageCacheOP = new ImageCacheOP(context);
        imageCacheOP.getImageFromURL(shoppingListBeans.get(position).getPhoto_doc() + "/1.png",
                shoppingListBeans.get(position).getCommodityId() + shoppingListBeans.
                        get(position).getSize(), imageHandler);
        viewHolder.tv_coast.setText(shoppingListBeans.get(position).getMoney());
        viewHolder.tv_count.setText(String.valueOf(shoppingListBeans.get(position).getCount()));
        viewHolder.tv_size.setText(shoppingListBeans.get(position).getSize());
        viewHolder.tv_goods_name.setText(shoppingListBeans.get(position).getBrandName());
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_goods;
        private TextView tv_goods_name;
        private TextView tv_count;
        private TextView tv_size;
        private TextView tv_count_title;
        private TextView tv_size_title;
        private TextView tv_coast;
        private ImageView iv_delete_goods;
        private LinearLayout ll_count;
        private LinearLayout ll_size;
    }
}
