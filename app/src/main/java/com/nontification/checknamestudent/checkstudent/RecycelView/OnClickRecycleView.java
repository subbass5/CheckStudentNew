package com.nontification.checknamestudent.checkstudent.RecycelView;

import android.view.MotionEvent;
import android.view.View;

public interface OnClickRecycleView {

    void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent);
    void onLongClick (View view, int position, boolean isLongClick, MotionEvent motionEvent);
}
