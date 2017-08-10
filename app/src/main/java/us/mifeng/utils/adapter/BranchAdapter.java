package us.mifeng.utils.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import us.mifeng.utils.R;

/**
 * Created by shido on 2017/7/5.
 */

public class BranchAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    public BranchAdapter(List<String> list,Context context){
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
            convertView = View.inflate(context, R.layout.branch_item,null);
            view.mTv = (TextView) convertView.findViewById(R.id.branch_item);
            //view.yanse = convertView.findViewById(R.id.yanse);
            convertView.setTag(view);
        }else {
            view = (ViewHorder) convertView.getTag();
        }
        view.mTv.setText(list.get(position));
        //view.yanse.setBackgroundColor(Color.parseColor("#ff0000"));
        return convertView;
    }
    public class ViewHorder{
        public TextView mTv;
        //public View yanse;
    }

}
