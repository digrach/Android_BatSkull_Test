package rach.android_swzom.framework.impl;

import java.util.List;

import rach.android_swzom.framework.Input.TouchEvent;
import android.view.View.OnTouchListener;


public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
