package us.mifeng.erjiliandong.ui.tab;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import us.mifeng.erjiliandong.ui.base.BaseViewGroupUtil;

/**
 * Created by shido on 2017/8/3.
 */

public class ListViewUtil extends BaseViewGroupUtil<ListView> {
    public ListViewUtil(ListView listView) {
        super(listView);
    }

    @Override
    public void updateEdges(){
        beginPos = mViewGroup.getFirstVisiblePosition();
        endPos = mViewGroup.getLastVisiblePosition();
    }

    @Override
    public void smoothScrollTo(int pos) {
        mViewGroup.smoothScrollToPosition(pos);
    }

    /**
     * 限定边界在 [beginPos, endPos] 之间
     * @param pos
     * @return
     */
    public int updatePosOnScrolled(int pos) {
        updateEdges();
        int newPos = pos;
        newPos = Math.max(newPos, beginPos);
        newPos = Math.min(newPos, endPos);
        setViewSelected(newPos);
        return newPos;
    }

    @Override
    protected void setViewSelected(View view, boolean isSelected){
        view.setBackgroundColor(isSelected? Color.WHITE: Color.LTGRAY);
    }
}
