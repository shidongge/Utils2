package us.mifeng.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.bean.TuiJian_Hor;
import us.mifeng.utils.http.WangZhi;



/**
 * Created by shido on 2017/9/5.
 */

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.VH> {
    private List<TuiJian_Hor> list;
    private Context context;
    public RecycAdapter(List<TuiJian_Hor> list,Context context){
        this.context=context;
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tuijian_hor, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mTv.setText(list.get(position).getMingcheng());

        //这里使用的是Frecso进行图片的加载
        holder.img.setImageURI(WangZhi.TUIJIAN_HOR+list.get(position).getTubiao());
       // Glide.with(context).load(WangZhi.TUIJIAN_HOR+list.get(position).getTubiao()).transform(new GlideRoundTransform(context,5)).into(holder.img);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class VH extends RecyclerView.ViewHolder {
        private TextView mTv;
        private SimpleDraweeView img;
        public VH(View itemView) {
            super(itemView);
            mTv=(TextView) itemView.findViewById(R.id.tuijian_hor_tv);
            img = (SimpleDraweeView) itemView.findViewById(R.id.tuijian_hor_img);
        }
    }
    
}
