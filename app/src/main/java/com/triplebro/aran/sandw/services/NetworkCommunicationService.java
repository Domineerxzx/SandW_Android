package com.triplebro.aran.sandw.services;

import android.app.Activity;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.triplebro.aran.sandw.activities.LoginActivity;
import com.triplebro.aran.sandw.activities.RegisterActivity;
import com.triplebro.aran.sandw.beans.AddAddressInfoBean;
import com.triplebro.aran.sandw.beans.AddressInfoBean;
import com.triplebro.aran.sandw.beans.BrandInfo;
import com.triplebro.aran.sandw.beans.ChangeAddressInfoBean;
import com.triplebro.aran.sandw.beans.FindInfo;
import com.triplebro.aran.sandw.beans.LoginInfoBean;
import com.triplebro.aran.sandw.beans.RegisterInfoBean;
import com.triplebro.aran.sandw.beans.ShopBagInfo;
import com.triplebro.aran.sandw.beans.ShowAddressInfoBean;
import com.triplebro.aran.sandw.beans.TenCommodityInfo;
import com.triplebro.aran.sandw.beans.TypeInfo;
import com.triplebro.aran.sandw.beans.UserInfo;
import com.triplebro.aran.sandw.databases.MyOpenHelper;
import com.triplebro.aran.sandw.handlers.AddAddressHandler;
import com.triplebro.aran.sandw.handlers.AddressHandler;
import com.triplebro.aran.sandw.handlers.BrandHandler;
import com.triplebro.aran.sandw.handlers.BrandListHandler;
import com.triplebro.aran.sandw.handlers.BrandOnClickHandler;
import com.triplebro.aran.sandw.handlers.ChangeAddressHandler;
import com.triplebro.aran.sandw.handlers.ChangeInfoHandler;
import com.triplebro.aran.sandw.handlers.ChangePassWordHandler;
import com.triplebro.aran.sandw.handlers.DeleteAddressHandler;
import com.triplebro.aran.sandw.handlers.FirstPageHandler;
import com.triplebro.aran.sandw.handlers.GoodInfoHandler;
import com.triplebro.aran.sandw.handlers.LoginHandler;
import com.triplebro.aran.sandw.handlers.LovesHandler;
import com.triplebro.aran.sandw.handlers.RegisterHandler;
import com.triplebro.aran.sandw.handlers.SearchHandler;
import com.triplebro.aran.sandw.handlers.SelectAllHandler;
import com.triplebro.aran.sandw.handlers.ShopBagHandler;
import com.triplebro.aran.sandw.handlers.ShowAddressInfoHandler;
import com.triplebro.aran.sandw.handlers.TypeHandler;
import com.triplebro.aran.sandw.handlers.TypeOnClickHandler;
import com.triplebro.aran.sandw.handlers.UserHandler;
import com.triplebro.aran.sandw.modules.AransModules;
import com.triplebro.aran.sandw.properties.AppProperties;
import com.triplebro.aran.sandw.utils.httpUtils.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

import static com.triplebro.aran.sandw.modules.AransModules.commodityId;


public class NetworkCommunicationService extends Service {

    private Gson gson = new Gson();

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void login(Context context, LoginHandler loginHandler, String email, String password) {
            NetworkCommunicationService.this.login(context, loginHandler, email, password);
        }

        public void register(Context context, RegisterHandler registerHandler, String nickname, String email, String password) {
            NetworkCommunicationService.this.register(context, registerHandler, nickname, email, password);
        }

        public void showUserInfo(Context context, UserHandler userHandler, String session, int messageWhat) {
            NetworkCommunicationService.this.showUserInfo(context, userHandler, session, messageWhat);
        }

        public void changeInfo(Context context, ChangeInfoHandler changeInfoHandler, String nickname, String email, String birthday, String sex, String session) {
            NetworkCommunicationService.this.changeInfo(context, changeInfoHandler, nickname, email, birthday, sex, session);
        }

        public void changePassword(Context context, ChangePassWordHandler changePassWordHandler, String old_password, String new_password, String session) {
            NetworkCommunicationService.this.changePassword(context, changePassWordHandler, old_password, new_password, session);
        }

        public void showAddress(Context context, AddressHandler addressHandler, String session) {
            NetworkCommunicationService.this.showAddress(context, addressHandler, session);
        }

        public void addAddress(Context context, AddAddressHandler addAddressHandler, AddAddressInfoBean addAddressInfoBean, String session) {
            NetworkCommunicationService.this.addAddress(context, addAddressHandler, addAddressInfoBean, session);
        }

        public void changeAddress(Context context, ChangeAddressHandler changeAddressHandler, ChangeAddressInfoBean changeAddressInfoBean, String session) {
            NetworkCommunicationService.this.changeAddress(context, changeAddressHandler, changeAddressInfoBean, session);
        }

        public void showAddressInfo(Context context, ShowAddressInfoHandler showAddressInfoHandler, String address_id) {
            NetworkCommunicationService.this.showAddressInfo(context, showAddressInfoHandler, address_id);
        }

        public void deleteAddress(Context context, DeleteAddressHandler deleteAddressHandler, String session, String address_id) {
            NetworkCommunicationService.this.deleteAddress(context, deleteAddressHandler, session, address_id);
        }

        public void getType(Context context, TypeHandler typeHandler) {
            NetworkCommunicationService.this.getType(context, typeHandler);
        }

        public void getGoodsInfo(Context context, FirstPageHandler firstPageHandler) {
            NetworkCommunicationService.this.getGoodsInfo(context, firstPageHandler);
        }

        public void getGoodsInfo(Context context, BrandHandler brandHandler) {
            NetworkCommunicationService.this.getGoodsInfo(context, brandHandler);
        }

        public void getBrand(Context context, BrandListHandler brandListHandler) {
            NetworkCommunicationService.this.getBrand(context, brandListHandler);
        }

        public void getGoodInfo(Context context, GoodInfoHandler goodInfoHandler) {
            NetworkCommunicationService.this.getGoodInfo(context, goodInfoHandler);
        }

        public void selectAll(Context context, SelectAllHandler selectAllHandler) {
            NetworkCommunicationService.this.selectAll(context, selectAllHandler);
        }

        public void find(Context context, SearchHandler searchHandler, String find) {
            NetworkCommunicationService.this.find(context, searchHandler, find);
        }

        public void showShopBag(Context context, ShopBagHandler shopBagHandler, String session) {
            NetworkCommunicationService.this.showShopBag(context, shopBagHandler, session);
        }

        public void deleteShopBag(Context context, ShopBagHandler shopBagHandler, int commodityId, String sizeName, String session, List<ShopBagInfo.ShoppingListBean> remove) {
            NetworkCommunicationService.this.deleteShopBag(context, shopBagHandler, session, commodityId, sizeName, remove);
        }

        public void addShopBag(Context context, ShopBagHandler shopBagHandler, String session, String commodityIds, String sizeName) {
            NetworkCommunicationService.this.addShopBag(context, shopBagHandler, session, commodityIds, sizeName);
        }

        public void startSelectAllActivity(Context context, String brandName, BrandOnClickHandler brandOnClickHandler) {
            NetworkCommunicationService.this.startSelectAllActivity(context, brandName, brandOnClickHandler);
        }

        public void startSelectAllActivity(Context context, String typeName, TypeOnClickHandler typeOnClickHandler) {
            NetworkCommunicationService.this.startSelectAllActivity(context, typeName, typeOnClickHandler);
        }

        public void getLovesList(Context context, LovesHandler lovesHandler, String session) {
            NetworkCommunicationService.this.getLovesList(context, lovesHandler, session);
        }

        public void addLovesList(Context context, LovesHandler lovesHandler, String session, String commodityId) {
            NetworkCommunicationService.this.addLovesList(context, lovesHandler, session, commodityId);
        }

        public void getGoods(Context context, SearchHandler searchHandler) {
            NetworkCommunicationService.this.getGoods(context, searchHandler);
        }

        public void getBrands(Context context, SearchHandler searchHandler) {
            NetworkCommunicationService.this.getBrands(context, searchHandler);
        }
    }

    private void getBrands(final Context context, final SearchHandler searchHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SEARCH_GET_BRANDS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        TenCommodityInfo tenCommodityInfo = gson.fromJson(res, TenCommodityInfo.class);
                        List<TenCommodityInfo.TencommodityBean> tenCommodity = tenCommodityInfo.getTencommodity();
                        Message message = Message.obtain();
                        message.obj = tenCommodity;
                        message.what = AppProperties.SEARCH_GET_GOODS;
                        searchHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取商品推荐信息信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void getGoods(final Context context, final SearchHandler searchHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SEARCH_GET_GOODS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        TenCommodityInfo tenCommodityInfo = gson.fromJson(res, TenCommodityInfo.class);
                        List<TenCommodityInfo.TencommodityBean> tenCommodity = tenCommodityInfo.getTencommodity();
                        Message message = Message.obtain();
                        message.obj = tenCommodity;
                        message.what = AppProperties.SEARCH_GET_GOODS;
                        searchHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取商品推荐信息信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void addLovesList(final Context context, final LovesHandler lovesHandler, String session, String commodityId) {
        final FormBody.Builder builder = new FormBody.Builder();
        if (session == null) {
            Toast.makeText(context, "尚未登录，心愿单数据已加到本地数据库中", Toast.LENGTH_SHORT).show();
            MyOpenHelper myOpenHelper = new MyOpenHelper(context);
            SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("commodityId", commodityId);
            writableDatabase.insert(AppProperties.LOVES_TABLE, null, contentValues);
        } else {
            builder.add("session", session);
            builder.add("commodityId", commodityId);
            new Thread() {
                @Override
                public void run() {
                    HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_ADD_LOVES_LIST, builder, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String res = response.body().string();
                            System.out.println(res);
                            Message message = Message.obtain();
                            message.obj = res;
                            message.what = AppProperties.LOVES_LIST_ADD;
                            lovesHandler.sendMessage(message);

                        }
                    });
                }
            }.start();
        }
    }

    private void addLovesList(final Context context, String session, String commodityId) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("commodityId", commodityId);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_ADD_LOVES_LIST, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "添加心愿单信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void getLovesList(final Context context, final LovesHandler lovesHandler, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        if (session != null) {
            builder.add("session", session);
        } else {
            Toast.makeText(context, "心愿单为空，请去添加心愿单", Toast.LENGTH_SHORT).show();
            Message obtain = Message.obtain();
            obtain.what = AppProperties.LOVES_LIST_EMPTY;
            lovesHandler.sendMessage(obtain);
            return;
        }
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_LOVES_LIST, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        message.what = AppProperties.LOVES_LIST_SHOW;
                        lovesHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取心愿单信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void startSelectAllActivity(final Context context, String typeName, final TypeOnClickHandler typeOnClickHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("rangeSearch", typeName);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SELECT_ALL_TYPE, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        typeOnClickHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取类别商品信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void startSelectAllActivity(final Context context, String brandName, final BrandOnClickHandler brandOnClickHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("brandSearch", brandName);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SELECT_ALL_BRAND, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        brandOnClickHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取品牌商品信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void addShopBag(final Context context, final ShopBagHandler shopBagHandler, String session, String commodityIds, String sizeName) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("commodityId", commodityIds);
        builder.add("sizeName", sizeName);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_ADD_SHOP_BAG, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.what = AppProperties.SHOP_BAG_ADD;
                        shopBagHandler.sendMessage(message);
                    }
                });
            }
        }.start();
    }

    private void deleteShopBag(final Context context, final ShopBagHandler shopBagHandler, String session, int commodityId, String sizeName, final List<ShopBagInfo.ShoppingListBean> remove) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("commodityId", String.valueOf(commodityId));
        builder.add("sizeName", sizeName);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_DELETE_SHOP_BAG, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.what = AppProperties.SHOP_BAG_DELETE;
                        message.obj = remove;
                        shopBagHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "删除购物袋信息成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void showShopBag(final Context context, final ShopBagHandler shopBagHandler, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SHOW_SHOP_BAG, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        ShopBagInfo shopBagInfo = gson.fromJson(res, ShopBagInfo.class);
                        List<ShopBagInfo.ShoppingListBean> shoppingList = shopBagInfo.getShoppingList();
                        if (shoppingList.size() == 0) {
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "购物袋为空", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Message message = Message.obtain();
                            message.what = AppProperties.SHOP_BAG_EMPTY;
                            shopBagHandler.sendMessage(message);
                        } else {
                            Message message = Message.obtain();
                            message.what = AppProperties.SHOP_BAG_SHOW;
                            message.obj = shopBagInfo;
                            shopBagHandler.sendMessage(message);
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "获取购物袋信息成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        }.start();
    }

    private void find(final Context context, final SearchHandler searchHandler, String find) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("description", find);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_FIND, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        FindInfo findInfo = gson.fromJson(res, FindInfo.class);
                        if(findInfo.getRangeSearch().size() == 0){
                            ((Activity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "未找到该数据", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }else{
                            Message message = Message.obtain();
                            message.what = AppProperties.SEARCH_FIND;
                            message.obj = res;
                            searchHandler.sendMessage(message);
                        }
                    }
                });
            }
        }.start();
    }

    private void selectAll(Context context, final SelectAllHandler selectAllHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("rangeSearch", AransModules.type);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SELECT_ALL_TYPE, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        selectAllHandler.sendMessage(message);
                    }
                });
            }
        }.start();
    }

    private void getGoodInfo(final Context context, final GoodInfoHandler goodInfoHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("commodityId", commodityId);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_GOOD_INFO, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        goodInfoHandler.sendMessage(message);
                    }
                });
            }
        }.start();
    }

    private void getBrand(final Context context, final BrandListHandler brandListHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_BRAND, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        BrandInfo brandInfo = gson.fromJson(res, BrandInfo.class);
                        Message message = Message.obtain();
                        message.obj = brandInfo;
                        brandListHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取品牌成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void getGoodsInfo(final Context context, final FirstPageHandler firstPageHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        String title = AransModules.title;
        if (title == null) {
            title = "连衣裙#手包";
        }
        builder.add("recommendation", title);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_GOODS_INFO, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        message.what = AppProperties.GET_GOODS_INFO;
                        firstPageHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取商品四格推荐成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void getGoodsInfo(final Context context, final BrandHandler brandHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        String title = AransModules.title;
        if (title == null) {
            title = "T恤";
        }
        builder.add("recommendation", title);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_GOODS_INFO, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        Message message = Message.obtain();
                        message.obj = res;
                        message.what = AppProperties.GET_GOODS_INFO;
                        brandHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取商品四格推荐成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void getType(final Context context, final TypeHandler typeHandler) {
        final FormBody.Builder builder = new FormBody.Builder();
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_GET_TYPE, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        System.out.println(res);
                        TypeInfo typeInfo = gson.fromJson(res, TypeInfo.class);
                        Message message = new Message();
                        message.obj = typeInfo;
                        typeHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取类别成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void deleteAddress(final Context context, final DeleteAddressHandler deleteAddressHandler, String session, String address_id) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("addressId", address_id);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_DELETE_ADDRESS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = Message.obtain();
                        deleteAddressHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "删除地址成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void showAddressInfo(final Context context, final ShowAddressInfoHandler showAddressInfoHandler, String address_id) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("addressId", address_id);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SHOW_ADDRESS_MORE, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        ShowAddressInfoBean showAddressInfoBean = gson.fromJson(res, ShowAddressInfoBean.class);
                        Message message = Message.obtain();
                        message.obj = showAddressInfoBean;
                        showAddressInfoHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "显示详细地址成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void changeAddress(final Context context, final ChangeAddressHandler changeAddressHandler, ChangeAddressInfoBean changeAddressInfoBean, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("addressId", changeAddressInfoBean.getAddressId());
        builder.add("surName", changeAddressInfoBean.getSurName());
        builder.add("name", changeAddressInfoBean.getName());
        builder.add("country", changeAddressInfoBean.getCountry());
        builder.add("province", changeAddressInfoBean.getProvince());
        builder.add("city", changeAddressInfoBean.getCity());
        builder.add("address", changeAddressInfoBean.getAddress());
        builder.add("postCode", changeAddressInfoBean.getPostCode());
        builder.add("phone", changeAddressInfoBean.getPhone());
        builder.add("option", changeAddressInfoBean.getOption());
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_CHANGE_ADDRESS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        changeAddressHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "修改地址成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void addAddress(final Context context, final AddAddressHandler addAddressHandler, AddAddressInfoBean addAddressInfoBean, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("surName", addAddressInfoBean.getSurName());
        builder.add("name", addAddressInfoBean.getName());
        builder.add("country", addAddressInfoBean.getCountry());
        builder.add("province", addAddressInfoBean.getProvince());
        builder.add("city", addAddressInfoBean.getCity());
        builder.add("address", addAddressInfoBean.getAddress());
        builder.add("postCode", addAddressInfoBean.getPostCode());
        builder.add("phone", addAddressInfoBean.getPhone());
        builder.add("option", addAddressInfoBean.getOption());
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_ADD_ADDRESS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        addAddressHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "添加地址成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void showAddress(final Context context, final AddressHandler addressHandler, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SHOW_ADDRESS, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();

                        if (!res.contains("\"ListNull\":false")) {
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "地址列表为空", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            AddressInfoBean addressInfoBean = gson.fromJson(res, AddressInfoBean.class);
                            Message message = new Message();
                            message.obj = addressInfoBean;
                            addressHandler.sendMessage(message);
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "显示地址成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        }.start();
    }

    private void changePassword(final Context context, final ChangePassWordHandler changePassWordHandler, String old_password, String new_password, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        builder.add("oldPassWd", old_password);
        builder.add("changePassWd", new_password);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_CHANGE_PASSWORD, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        changePassWordHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "修改密码成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void changeInfo(final Context context, final ChangeInfoHandler changeInfoHandler, String nickname, String email, String birthday, String sex, String session) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("nickName", nickname);
        builder.add("birthday", birthday);
        builder.add("userName", email);
        builder.add("sex", sex);
        builder.add("session", session);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_CHANGE_USER_INFO, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        changeInfoHandler.sendMessage(message);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    private void showUserInfo(final Context context, final UserHandler userHandler, String session, final int messageWhat) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("session", session);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_SHOW_USER_INFO, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        UserInfo userInfo = gson.fromJson(res, UserInfo.class);
                        if (userInfo.isSessionProve()) {
                            Log.i("ServerBackCode(服务器返回):", "显示成功");
                            Message message = new Message();
                            message.obj = userInfo.getUserInfo();
                            message.what = messageWhat;
                            userHandler.sendMessage(message);
                        } else {
                            Log.i("ServerBackCode(服务器返回):", "显示失败");
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "显示失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }
        }.start();
    }

    private void register(final Context context, final RegisterHandler registerHandler, final String nickname, final String email, final String password) {
        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("nickName", nickname);
        builder.add("userName", email);
        builder.add("passWord", password);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_REGISTER, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        RegisterInfoBean registerInfoBean = gson.fromJson(res, RegisterInfoBean.class);
                        if (registerInfoBean.isResult()) {
                            Log.i("ServerBackCode(服务器返回):", "注册成功");
                            SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("session", registerInfoBean.getSession());
                            edit.commit();
                            Message message = new Message();
                            message.obj = res;
                            registerHandler.sendMessage(message);
                        } else {
                            Log.i("ServerBackCode(服务器返回):", "注册失败");
                            ((RegisterActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        }.start();
    }

    private void login(final Context context, final LoginHandler loginHandler, String email, String password) {

        final FormBody.Builder builder = new FormBody.Builder();
        builder.add("userName", email);
        builder.add("passWord", password);
        new Thread() {
            @Override
            public void run() {
                HttpUtils.sendOkHttpRequest(AppProperties.SERVER_ADDRESS_OF_LOGIN, builder, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ((LoginActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "未连接到服务器，请检查网络！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String res = response.body().string();
                        LoginInfoBean loginInfoBean = gson.fromJson(res, LoginInfoBean.class);
                        if (loginInfoBean.isResult()) {
                            Log.i("ServerBackCode(服务器返回):", "登录成功");
                            SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("session", loginInfoBean.getSession());
                            edit.commit();
                            MyOpenHelper myOpenHelper = new MyOpenHelper(context);
                            SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
                            //writableDatabase.delete(AppProperties.LOVES_TABLE, "_id = ?", new String[]{"0"});
                            Cursor query = writableDatabase.query(AppProperties.LOVES_TABLE, null, null, null, null, null, null);
                            if (query != null && query.getCount() > 0) {
                                while (query.moveToNext()) {
                                    String commodityId = query.getString(1);
                                    addLovesList(context, loginInfoBean.getSession(), commodityId);
                                }
                            }
                            writableDatabase.delete(AppProperties.LOVES_TABLE, null, null);
                            query.close();
                            writableDatabase.close();
                            Message message = new Message();
                            message.obj = res;
                            loginHandler.sendMessage(message);
                        } else {
                            Log.i("ServerBackCode(服务器返回):", "登录失败");
                            ((LoginActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "输入的邮箱或密码不正确！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        }.start();
    }

}
