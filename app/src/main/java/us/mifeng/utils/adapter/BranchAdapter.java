package us.mifeng.utils.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.bean.FenLeiBean;
import us.mifeng.utils.http.WangZhi;
import us.mifeng.utils.view.GlideCircleTransform;

/**
 * Created by shido on 2017/7/5.
 */

public class BranchAdapter extends BaseAdapter {
    private List<FenLeiBean> list;
    private Context context;
    public BranchAdapter(List<FenLeiBean> list, Context context){
        this.list=list;
        this.context=context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorder view = null;
        if (view==null){
            view = new ViewHorder();
            convertView = View.inflate(context, R.layout.right_item,null);
            view.mTv = (TextView) convertView.findViewById(R.id.grid_right_mTv);
            view.mIv = (ImageView) convertView.findViewById(R.id.grid_right_img);
            convertView.setTag(view);
        }else {
            view = (ViewHorder) convertView.getTag();
        }
        view.mTv.setText(list.get(position).getMingcheng());
        Glide.with(context).load(WangZhi.BRANCH+list.get(position).getLogo()).bitmapTransform(new GlideCircleTransform(context)).into(view.mIv);
        return convertView;
    }
    public class ViewHorder{
        public TextView mTv;
        public ImageView mIv;

    }

}
