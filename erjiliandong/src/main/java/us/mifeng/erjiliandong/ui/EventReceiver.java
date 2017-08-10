package us.mifeng.erjiliandong.ui;

/**
 * Created by shido on 2017/8/3.
 */

public interface EventReceiver {
    /**
     * 收到事件: 立即选中 newPos
     * @param newPos
     */
    void selectItem(int newPos);
}
