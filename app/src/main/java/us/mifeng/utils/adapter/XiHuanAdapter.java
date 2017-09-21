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
import us.mifeng.utils.bean.XiHuanBean;
import us.mifeng.utils.http.WangZhi;
import us.mifeng.utils.view.GlideRoundTransform;

/**
 * Created by shido on 2017/7/10.
 */

public class XiHuanAdapter extends BaseAdapter {
    private List<XiHuanBean> list ;
    private Context context;
    public XiHuanAdapter(List<XiHuanBean> list,Context context){
        this.list = list;
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
        MyViewHorder vh =null;
        if (vh==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.gl_item,null);
            vh.id = (TextView) convertView.findViewById(R.id.price);
            vh.title = (TextView) convertView.findViewById(R.id.title);
            vh.img = (ImageView) convertView.findViewById(R.id.gl_item_mIv);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.id.setText(list.get(position).getJianjie());
        vh.title.setText(list.get(position).getTitle());
        Glide.with(context).load(WangZhi.HOME_DIANPU+list.get(position).
                getImgUrl()).
                crossFade().
                transform(new GlideRoundTransform(context)).
                into(vh.img);
        return convertView;
    }
    class MyViewHorder{
        TextView id,title;
        ImageView img;
    }
}
