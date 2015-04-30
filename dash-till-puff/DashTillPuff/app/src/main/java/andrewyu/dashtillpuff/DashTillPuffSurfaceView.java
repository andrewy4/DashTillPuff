package andrewyu.dashtillpuff;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Chau on 4/29/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback , TimeConscious {
        public DashTillPuffRenderThread renderThread;


        public DashTillPuffSurfaceView ( Context context ) {
            super ( context ) ;
            getHolder () . addCallback ( this ) ;
        }
        @Override
        public void surfaceCreated ( SurfaceHolder holder ) {
            renderThread = new DashTillPuffRenderThread ( this ) ;
            renderThread . start () ;

// Create the sliding background , cosmic factory , trajectory
// and the space ship

        }
        @Override
        public boolean onTouchEvent ( MotionEvent e ) {
            switch ( e . getAction () ) {
                case MotionEvent . ACTION_DOWN : // Thrust the space ship up .
                    break ;
                case MotionEvent . ACTION_UP : // Let space ship fall freely .
                    break ;
            }
            return true ;
        }
        @Override
        public void onDraw ( Canvas c ) {
            super . onDraw ( c ) ;
// Draw everything ( restricted to the displayed rectangle ) .
        }


    @Override
    public void surfaceChanged ( SurfaceHolder holder ,int format , int width , int height ) {
// Respond to surface changes , e . g . , aspect ratio changes .
    }
    @Override
    public void surfaceDestroyed ( SurfaceHolder holder ) {
    // The cleanest way to stop a thread is by interrupting it .
    // BubbleShooterThread regularly checks its interrupt flag .
        renderThread . interrupt () ;
    }




        @Override
        public void tick( Canvas c ) {

// Tick background , space ship , cosmic factory , and trajectory .
// Draw everything ( restricted to the displayed rectangle ) .
        }
    }


