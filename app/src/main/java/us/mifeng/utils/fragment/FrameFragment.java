package us.mifeng.utils.fragment;

import android.view.View;
import android.widget.TextView;

import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseFragment;

/**
 * Created by shido on 2017/7/10.
 */

public class FrameFragment extends BaseFragment {
    public static final String TAG = "FrameFragment";
    private String str;
    @Override
    protected View initView() {
       View view =  View.inflate(getActivity(), R.layout.myfragment,null);
        TextView tv = (TextView) view.findViewById(R.id.frame_tv);
        //得到数据
        str = getArguments().getString(TAG);
        tv.setText(str);
        return view;
    }

    @Override
    protected void initList() {

    }
}
