package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.triplebro.aran.sandw.adapters.TypeContentAdapter;
import com.triplebro.aran.sandw.beans.TypeInfo;
import com.triplebro.aran.sandw.managers.TypeManager;

import java.util.List;

public class TypeHandler extends Handler {

    private Context context;
    private ListView lv_type;
    private TypeManager typeManager;
    private int sex;

    public TypeManager getTypeManager() {
        return typeManager;
    }

    public void setTypeManager(TypeManager typeManager) {
        this.typeManager = typeManager;
    }

    public TypeHandler(Context context, ListView lv_type, int sex) {
        this.context = context;
        this.lv_type = lv_type;
        this.sex = sex;
    }

    @Override
    public void handleMessage(Message msg) {
        TypeInfo typeInfo = (TypeInfo) msg.obj;
        List<TypeInfo.BigRangeListBean> bigRangeList = typeInfo.getBigRangeList();
        TypeInfo.BigRangeListBean bigRangeListBean = bigRangeList.get(sex);
        List<TypeInfo.BigRangeListBean.ClassValueBean> classValue = bigRangeListBean.getClassValue();
        TypeContentAdapter typeContentAdapter = new TypeContentAdapter(context, classValue);
        lv_type.setAdapter(typeContentAdapter);
        context.unbindService(typeManager);
    }
}
