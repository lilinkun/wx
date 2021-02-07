package com.android.wx.utils;

import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Description TODO
 * @Date 2021/1/27 14:07
 */
public class TimeUtil {

    public static String timeToDate(long time){
        int t = (int)time/1000;

        String mStr = "";
        String nStr = "";

        int m = t/3600;

        int n = t/60;

        if (n > 60){
            n -= 60;
        }

        if(m < 10){
            mStr = "0"+m;
        }else {
            mStr = m+"";
        }
        if(n < 10){
            nStr = "0"+n;
        }else {
            nStr = n+"";
        }

        return mStr + ":" + nStr;
    }

    public static String timeOld(long oldTime){

        int time = (int)((new Date()).getTime() - Long.valueOf(oldTime))/1000;

        String mStr = "";

        if(time < 60){
            mStr = "1分钟内";
        }else if (60 < time && time < 3600){
            mStr = time / 60 + "分钟前";
        }else if (3600 < time && time < 86400){
            mStr = time / 3600 + "小时内";
        }else if (86400 < time){
            mStr = time / 86400 + "天内";
        }

        return mStr;

    }



}
