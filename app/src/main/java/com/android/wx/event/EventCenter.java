package com.android.wx.event;

/**
 * Created by kai on 2017-07-29.
 */

public class EventCenter<T> {
    private T data ; //Event事件中传递的数据

    private int eventCode = -1 ; //Event事件中传递的标识位，用int标识

    public EventCenter(int eventCode , T data){
        this.data = data ;
        this.eventCode = eventCode ;
    }

    public EventCenter(int eventCode){
        this(eventCode,null);
    }

    /**
     * 获取Event事件中传递的标识位
     * @return
     */
    public int getEventCode(){
        return eventCode ;
    }

    /**
     * 获取Event事件中包含的数据
     * @return
     */
    public T getEventData(){
        return data ;
    }
}
