package us.mifeng.utils.fragment;

import android.view.View;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import us.mifeng.utils.R;
import us.mifeng.utils.base.BaseFragment;
import us.mifeng.utils.view.MyGridView;

/**
 * Created by shido on 2017/6/28.
 */

/**
 * 购物车fragment
 */
public class ShopFragment extends BaseFragment {

    private SwipeToLoadLayout swipeToLoadLayout;
    private MyGridView mGr;

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.shopfragment, null);
        mGr = (MyGridView) inflate.findViewById(R.id.shop_mGr);
        swipeToLoadLayout = (SwipeToLoadLayout) inflate.findViewById(R.id.shop_tianjia);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置下拉刷新结束
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        return inflate;
    }

    @Override
    protected void initList() {

    }
}
