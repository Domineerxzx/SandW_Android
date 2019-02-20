package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.beans.TenCommodityInfo;
import com.triplebro.aran.sandw.cache.ImageCacheOP;
import com.triplebro.aran.sandw.handlers.ImageHandler;

import java.util.List;

public class MaybeAdapter extends RecyclerView.Adapter<MaybeAdapter.ViewHolder> {

    private Context context;
    private List<TenCommodityInfo.TencommodityBean> data;
    private ImageCacheOP imageCacheOP;

    public MaybeAdapter(Context context, List<TenCommodityInfo.TencommodityBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_search_maybe, null);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.iv_maybe = view.findViewById(R.id.iv_maybe);
        viewHolder.tv_maybe = view.findViewById(R.id.tv_maybe);
        viewHolder.tv_maybe_more = view.findViewById(R.id.tv_maybe_more);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_maybe.setText(data.get(position).getBrandName());
        String fileName = data.get(position).getBrandName()+"";
        ImageHandler imageHandler = new ImageHandler(context, holder.iv_maybe, fileName);
        imageCacheOP = new ImageCacheOP(context);
        imageCacheOP.getImageFromURL(data.get(position).getPhotoDoc() + "1.png",
                fileName, imageHandler);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_maybe;
        private TextView tv_maybe;
        private TextView tv_maybe_more;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
