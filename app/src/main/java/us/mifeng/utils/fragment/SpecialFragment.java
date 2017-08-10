package us.mifeng.utils.fragment;

import android.view.View;
import android.widget.TextView;

import us.mifeng.utils.base.BaseFragment;

/**
 * Created by shido on 2017/6/28.
 */

/**
 * 专题fragment
 */
public class SpecialFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("我是专题");

        return textView;
    }

    @Override
    protected void initList() {

    }
}
