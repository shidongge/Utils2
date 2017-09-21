package us.mifeng.utils.ui.adactivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.http.WangZhi;
import us.mifeng.utils.utils.DateUtils;

/**
 * Created by shido on 2017/9/13.
 */

public class AD_PL_Adapter extends BaseAdapter {
    private List<AD_PL_Bean.ObjBean.ListBean> list;
    private Context context;
    public AD_PL_Adapter(Context context,List<AD_PL_Bean.ObjBean.ListBean> list){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        if (list.size()>0){
            return list.size();
        }
        return 0;
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
        MyViewHorlder vh = null;
        if (vh==null){
            vh = new MyViewHorlder();
            convertView = View.inflate(context, R.layout.ad_lv_pl_item,null);
            vh.photo = (ImageView) convertView.findViewById(R.id.ad_pl_photo);
            vh.name = (TextView) convertView.findViewById(R.id.ad_pl_name);
            vh.shijian = (TextView) convertView.findViewById(R.id.ad_pl_shijian);
            vh.pinlun = (TextView) convertView.findViewById(R.id.ad_pl_pinglun);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorlder) convertView.getTag();
        }
        vh.pinlun.setText(list.get(position).getNeirong());
        vh.name.setText(list.get(position).getUser().getNickname());
        String pinglunshu = list.get(position).getPinglunshu();
        if ("0".equals(pinglunshu)){
            vh.shijian.setVisibility(View.GONE);

        }else {
            vh.shijian.setVisibility(View.VISIBLE);
            DateUtils dateUtils = new DateUtils();
            vh.shijian.setText( dateUtils.getStrTime(list.get(position).getShijian()));
        }
        String avatar = list.get(position).getUser().getAvatar();
        Glide.with(context).load(WangZhi.AVATAR+list.get(position).getUser().getAvatar()).into(vh.photo);
        return convertView;
    }

    class MyViewHorlder{
        TextView name,shijian,pinlun;
        ImageView photo;
    }

}
