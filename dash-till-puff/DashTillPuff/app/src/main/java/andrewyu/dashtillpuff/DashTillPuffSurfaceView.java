package andrewyu.dashtillpuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Chau on 4/29/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback , TimeConscious {
        public DashTillPuffRenderThread renderThread;
        public Bitmap bitmap;
        public float Width;
        public float Height;
        public Trajectory trajectory;



        public float returnWidth(){
            return getWidth();
        }

        public float returnHeight(){
            return getHeight();
        }

        public DashTillPuffSurfaceView ( Context context ) {
            super ( context ) ;
            getHolder () . addCallback ( this ) ;
            setFocusable(true);
        }
        @Override
        public void surfaceCreated ( SurfaceHolder holder ) {
            Width = getWidth();
            Height = getHeight();
            BitmapFactory.Options options = new BitmapFactory.Options();
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dashtillpuffwallpaper,options);
            trajectory = new Trajectory(Width, Height);
            renderThread = new DashTillPuffRenderThread ( this ) ;
            renderThread . start () ;


// Create the sliding background , cosmic factory , trajectory
// and the space ship

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
        public void tick( Canvas c ) {
// Tick background , space ship , cosmic factory , and trajectory .
// Draw everything ( restricted to the displayed rectangle ) .

            Paint paint = new Paint();
            paint.setAlpha(255);
            Rect dst = new Rect(0,0,getWidth(),getHeight());
            c.drawBitmap(bitmap,null,dst,paint);
            //bitmap declared earlier to be the background pic.

            trajectory.tick(c);

        }
    }


