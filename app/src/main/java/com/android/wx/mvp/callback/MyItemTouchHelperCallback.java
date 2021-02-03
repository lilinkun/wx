package com.android.wx.mvp.callback;

import android.graphics.Canvas;
import android.graphics.Color;

import com.android.wx.R;
import com.android.wx.interf.ItemTouchMoveListener;

import javax.security.auth.callback.Callback;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;


/**
 * @ClassName MyItemTouchHelperCallback
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/31 22:00
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchMoveListener moveListener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener moveListener) {
        this.moveListener = moveListener;
    }

    //最先调用，判断ITEM触摸拖动方向，如上下左右拖动；滑动方向SWIPE 左右滑动
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {

        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);

        return flags;
    }

    /**
     * 是否长按拖动
     * @return true长按拖动
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 拖动时ITEM时，调用adapter里的onItemMove函数实现ITEM交换位置与交换数据
     * @param recyclerView： 列表控件
     * @param srcHolder      源ITEM，
     * @param targetHolder   目标ITEM，即拖动条目时要和这个ITEM交换位置
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
        if(srcHolder.getItemViewType()!=targetHolder.getItemViewType()){
            return false;
        }
        boolean result = moveListener.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return result;
    }

    /**
     * swipe侧滑时调用adapter里的onItemRemove函数实现ITEM删除
     * @param holder
     * @param arg1
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int arg1) {
        moveListener.onItemRemove(holder.getAdapterPosition());
    }

    /**
     * 拖动时ITEM背景色
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断选中状态
        if(actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorPrimary));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 交换数据后停止拖动，背景色恢复为白色
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 重新绘制ITEM的大小等。例如在这里滑动删除时，让ITEM逐渐缩小，并逐渐变成透明而消失
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {

        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            //透明度动画
            float alpha = 1-Math.abs(dX)/viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);//1~0
            viewHolder.itemView.setScaleX(alpha);//1~0
            viewHolder.itemView.setScaleY(alpha);//1~0
        }


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                isCurrentlyActive);
    }


}