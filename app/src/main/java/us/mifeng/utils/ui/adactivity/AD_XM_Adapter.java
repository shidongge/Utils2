package us.mifeng.utils.ui.adactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import us.mifeng.utils.R;
import us.mifeng.utils.http.WangZhi;

/**
 * Created by shido on 2017/9/12.
 */

public class AD_XM_Adapter extends BaseAdapter {
    //http://www.quanminlebang.com/photo/fuwu/1500979171798.jpg
    private List<AD_XM_Bean.ObjBean.ListBean> list;
    private Context context;
    public AD_XM_Adapter(List<AD_XM_Bean.ObjBean.ListBean> list, Context context){
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
        MyViewHorder vh = null;
        if (vh==null){
            vh = new MyViewHorder();
            convertView = LayoutInflater.from(context).inflate(R.layout.ad_lv_xm_item,null);
            vh.yuyue = (LinearLayout) convertView.findViewById(R.id.ad_lv_xm_yuyue);
            vh.danwei = (TextView) convertView.findViewById(R.id.ad_lv_xm_danwei);

            vh.yuyueshu = (TextView) convertView.findViewById(R.id.ad_lv_xm_yuyueshu);
            vh.img = (ImageView) convertView.findViewById(R.id.ad_lv_xm_img);
            vh.jianjie = (TextView) convertView.findViewById(R.id.ad_lv_xm_jianjie);
            vh.jine = (TextView) convertView.findViewById(R.id.ad_lv_xm_jine);
            vh.name = (TextView) convertView.findViewById(R.id.ad_lv_xm_name);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.jine.setText(list.get(position).getJine());
        vh.danwei.setText(list.get(position).getDanwei());
        vh.name.setText(list.get(position).getName());
        vh.jianjie.setText(list.get(position).getJianjie());
        Glide.with(context).load(WangZhi.FUWU+list.get(position).getPhoto()).into(vh.img);
        int yuyueshu = list.get(position).getYuyueshu();
        if (0==yuyueshu){
            vh.yuyue.setVisibility(View.GONE);
        }else {
            vh.yuyue.setVisibility(View.VISIBLE);
            vh.yuyueshu.setText(yuyueshu+"");
        }

        return convertView;
    }
    class MyViewHorder{
        LinearLayout yuyue;
        TextView name,jianjie,danwei,jine,yuyueshu;
        ImageView img;
    }
}
