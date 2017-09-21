package us.mifeng.utils.ui.adactivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import us.mifeng.utils.R;

/**
 * Created by shido on 2017/9/12.
 */

public class AD_RY_Adapter extends BaseAdapter{
    private List<AD_RY_Bean.ObjBean.ListBean> list;
    private Context context;
    public AD_RY_Adapter(Context context,List<AD_RY_Bean.ObjBean.ListBean> list){
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
        MyViewHorder vh =null;
        if (vh==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.ad_lv_ry_item,null);
            vh.name = (TextView) convertView.findViewById(R.id.ad_ry_name);
            vh.yuyue = (LinearLayout) convertView.findViewById(R.id.ad_ry_yudan);
            vh.yuyueshu = (TextView) convertView.findViewById(R.id.ad_ry_yuyueshu);
            convertView.setTag(vh);


        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.name.setText(list.get(position).getShanghunicheng());
        String jiedanshu = list.get(position).getJiedanshu();
        if ("0".equals(jiedanshu)){
            vh.yuyue.setVisibility(View.GONE);
        }else {
            vh.yuyue.setVisibility(View.VISIBLE);
            vh.yuyueshu.setText(list.get(position).getJiedanshu());
        }


        return convertView;
    }

    class MyViewHorder{
        TextView name,yuyueshu;
        LinearLayout yuyue;
    }
}
