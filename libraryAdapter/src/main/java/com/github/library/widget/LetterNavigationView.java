package com.github.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/11 0011.
 */
public class LetterNavigationView extends View {

    private Context mContext;
    private Paint mPaint;
    private Paint mCirclePaint;

    private List<String> mDatas;

    private int mHeight;
    private int mWidth;
    private int mTextHeight;

    private int selectorPosition;

    private Paint.FontMetrics mFontMetrics;

    private OnTouchListener mOnTouchListener;

    public LetterNavigationView(Context context) {
        this(context, null);
    }

    public LetterNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 单位是像素
         */
        mPaint.setTextSize(15);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#0e0e0e"));
        mDatas = new ArrayList<>();
        selectorPosition = 0;

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#0e0e0e"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        if (!mDatas.isEmpty()) {
            mTextHeight = (mHeight / mDatas.size());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mDatas.size(); i++) {
            if (i == selectorPosition) {
//                mPaint.setColor(Color.GRAY);
//                canvas.drawCircle(mWidth / 2, i * mTextHeight + mTextHeight / 2 - dip2px(1), dip2px(8), mCirclePaint);
            } else {
                mPaint.setColor(Color.BLACK);
            }
            mPaint.setTextSize(dip2px(10));
            mFontMetrics = mPaint.getFontMetrics();
            canvas.drawText(mDatas.get(i), mWidth / 2, i * mTextHeight + mTextHeight / 2 + mFontMetrics.bottom, mPaint);
//            canvas.drawText(mDatas.get(i), mAvgWidth, mAvgHeight * (i + 1), mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changePosition(y);
                setBackgroundResource(R.drawable.slidebar_bk);
                break;
            case MotionEvent.ACTION_MOVE:
                changePosition(y);
                setBackgroundResource(R.drawable.slidebar_bk);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if (mOnTouchListener != null) {
                    mOnTouchListener.onTouchListener(mDatas.get(selectorPosition), true);
                }
                break;
        }
        return true;
    }

    /**
     * @param y
     */
    private void changePosition(int y) {
        selectorPosition = y / (mHeight / mDatas.size());
        if (selectorPosition >= mDatas.size()) {
            selectorPosition = mDatas.size() - 1;
        } else if (selectorPosition <= 0) {
            selectorPosition = 0;
        }
        if (mOnTouchListener != null) {
            mOnTouchListener.onTouchListener(mDatas.get(selectorPosition), false);
        }
        invalidate();
    }
    private int mAvgWidth;
    private int mAvgHeight;
    /**
     * @param position
     */
    public void setSelectorPosition(int position) {
        selectorPosition = position;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        //计算每个字母所占区域的宽度和高度
        mAvgWidth = measuredWidth / 2;
//        mAvgHeight = measuredHeight / mDatas.size();
    }
    /**
     * @param datas
     */
    public void setData(List<String> datas) {
        mDatas = datas;
    }

    public interface OnTouchListener {
        void onTouchListener(String str, boolean hideEnable);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    /**
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}