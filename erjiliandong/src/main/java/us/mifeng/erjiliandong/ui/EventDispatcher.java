package us.mifeng.erjiliandong.ui;

import android.view.View;

/**
 * Created by shido on 2017/8/3.
 */

public interface EventDispatcher {
    /**
     * 分发事件: fromView 中的 pos 被选中
     * @param pos
     * @param fromView
     */
    void dispatchItemSelectedEvent(int pos, View fromView);
}
