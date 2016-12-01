package com.xiseven.diycode.bean;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class MessageEvent {
    private int what;
    private Object object;

    public MessageEvent(Object object, int what) {
        this.object = object;
        this.what = what;
    }

    public MessageEvent(int what) {
        this.what = what;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
