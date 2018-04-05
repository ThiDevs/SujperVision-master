package alves.thiago.supervision;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener implements GestureDetector.OnGestureListener {

    public GestureListener() {
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d("GESTURES", "onFling: ");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
      //  Log.d("GESTURES", "onFling: ");

    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        Log.d("GESTURES", "onFling: " + event1.toString()+event2.toString());
        return false;
    }
}