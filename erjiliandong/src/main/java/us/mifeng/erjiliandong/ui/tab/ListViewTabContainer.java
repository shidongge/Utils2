package us.mifeng.erjiliandong.ui.tab;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ListView;

import us.mifeng.erjiliandong.ui.EventReceiver;
import us.mifeng.erjiliandong.ui.base.BaseScrollableContainer;
import us.mifeng.erjiliandong.ui.base.BaseViewGroupUtil;

/**
 * Created by shido on 2017/8/3.
 */

public class ListViewTabContainer extends BaseScrollableContainer<ListView> implements EventReceiver {
    public ListViewTabContainer(Context context, ListView listView) {
        super(context, listView);
    }

    @Override
    protected BaseViewGroupUtil<ListView> getViewUtil() {
        return new ListViewUtil(mViewGroup);
    }

    @Override
    protected void setOnScrollListener() {
        mViewGroup.setOnScrollListener(new ProxyOnScrollListener());

        mViewGroup.setOnItemClickListener((parent, view, position, id) ->
                mRealOnScrollListener.onClick(position)
        );
    }

    public class ProxyOnScrollListener implements AbsListView.OnScrollListener{
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState == SCROLL_STATE_IDLE) {              // 停止滑动
                mRealOnScrollListener.onScrollStop();
            }else if(scrollState == SCROLL_STATE_TOUCH_SCROLL)  // 按下拖动
                mRealOnScrollListener.onScrollStart();
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            mRealOnScrollListener.onScrolled();                 // 滑动
        }
    }
}
