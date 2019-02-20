package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageHandler extends Handler {

    private Context context;
    private ImageView iv_goods;
    private String fileName;

    public ImageHandler(Context context, ImageView iv_goods,String fileName) {
        this.context = context;
        this.iv_goods = iv_goods;
        this.fileName = fileName;
    }

    @Override
    public void handleMessage(Message msg) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(context.getCacheDir()+"/"+fileName+".png", null);
        iv_goods.setImageBitmap(bitmap);
    }
}
