package com.android.wx.interf;

/**
 * @ClassName ItemTouchMoveListener
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/31 22:03
 */
public interface ItemTouchMoveListener {

    /**
     * 当拖拽的时候回调,交换fromPosition与toPosition
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * 当条目被移除是回调，删除position条目
     */
    boolean onItemRemove(int position);
}