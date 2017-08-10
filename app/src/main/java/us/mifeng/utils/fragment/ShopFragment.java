package us.mifeng.utils.fragment;

import android.view.View;
import android.widget.TextView;

import us.mifeng.utils.base.BaseFragment;

/**
 * Created by shido on 2017/6/28.
 */

/**
 * 购物车fragment
 */
public class ShopFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("购物车fragment");
        return textView;
    }

    @Override
    protected void initList() {

    }
}
