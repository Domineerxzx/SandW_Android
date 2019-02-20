package com.triplebro.aran.sandw.cache;

import android.content.Context;
import android.os.Message;
import android.util.Base64;

import com.triplebro.aran.sandw.handlers.ImageHandler;
import com.triplebro.aran.sandw.utils.httpUtils.HttpStreamOP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ImageCacheOP {
    private Context context;

    public ImageCacheOP(Context context) {
        this.context = context;
    }

    public void getImageFromURL(final String path, final String fileName, final ImageHandler imageHandler){
        new Thread(){
            @Override
            public void run() {
                InputStream in = null;
                File file = new File(context.getCacheDir(),fileName+".png");
                if(file.exists()&&file.length()>0){
                    System.out.println("----------------cache----------------");
                    imageHandler.sendMessage(new Message());
                }else{
                    try {
                        in = new HttpStreamOP().getInputStream(path);
                        if(in!=null){
                            writeImageToCache(in,file,imageHandler);
                        }
                        System.out.println("------------------server-------------------");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            in.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
    private void writeImageToCache(InputStream in, File file, ImageHandler imageHandler) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = -1;
            byte[] buffer = new byte[1024];
            while((len = in.read(buffer))!=-1){

                fos.write(buffer,0,len);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                imageHandler.sendMessage(new Message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
