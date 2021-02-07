package com.android.wx.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * @ClassName IWxConstant
 * @Description TODO
 * @Date 2021/1/28 1:35
 */
public class IWxConstant {

    public static final int DAY = 0;
    public static final int WEEK = 1;
    public static final int MONTH = 2;
    public static final int YEAR = 3;

    public static final Display display(Context context){
        WindowManager wm = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        Display defaultDisplay = wm.getDefaultDisplay();
        return defaultDisplay;
    }

    public static boolean isNull(EditText editText){
        if (editText != null  && editText.getText().toString() != null && editText.getText().toString().trim().length() > 0){
            return true;
        }
        return false;
    }

    public static void isEmpty(EditText editText){
        if (editText != null  && editText.getText().toString() != null ) {
            editText.setText("");
        }
    }

}
