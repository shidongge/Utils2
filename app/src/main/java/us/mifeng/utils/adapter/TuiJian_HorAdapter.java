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
import us.mifeng.utils.bean.TuiJian_Hor;
import us.mifeng.utils.http.WangZhi;

/**
 * Created by shido on 2017/9/5.
 */

public class TuiJian_HorAdapter extends BaseAdapter {
    private List<TuiJian_Hor> list;
    private Context context;
    public TuiJian_HorAdapter(Context context,List<TuiJian_Hor> list){
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
        ViewHolder vh = null;
        if (vh ==null){
            vh = new ViewHolder();
            convertView = View.inflate(context,R.layout.tuijian_hor,null);
            vh.tubiao = (ImageView) convertView.findViewById(R.id.tuijian_hor_img);
            vh.mingcheng = (TextView) convertView.findViewById(R.id.tuijian_hor_tv);
            convertView.setTag(vh);
        } else {
          vh = (ViewHolder) convertView.getTag();
        }
        vh.mingcheng.setText(list.get(position).getMingcheng());
        Glide.with(context).load(WangZhi.TUIJIAN_HOR+list.get(position).getTubiao()).into(vh.tubiao);
        return convertView;
    }
    public class ViewHolder{
        private TextView mingcheng;
        private ImageView tubiao;
    }
}
