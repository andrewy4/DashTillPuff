package andrewyu.dashtillpuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/*
 * Created by Chau on 4/29/2015.
 */

public class DashTillPuffSurfaceView extends SurfaceView implements SurfaceHolder.Callback , TimeConscious {
        public DashTillPuffRenderThread renderThread;
        Bitmap bitmap, gameOverPaper, startMenu;
        public float Width;
        public float Height;
        public Trajectory trajectory;
        public CosmicFactory cosmicFactory;
        public Rocket rocket;
        public boolean gameOver;
        public boolean start;
        public Paint paint;
        public Rect dst;
        public DashTillPuffSurfaceView ( Context context ) {
            super ( context ) ;
            getHolder () . addCallback ( this ) ;
            setFocusable(true);
        }
        @Override
        public void surfaceCreated ( SurfaceHolder holder ) {
            Width = getWidth();
            Height = getHeight();
            gameOver = false;
            start = false;
            BitmapFactory.Options options = new BitmapFactory.Options();
            bitmap =BitmapFactory.decodeResource(getResources(),R.drawable.dashtillpuffwallpaper,options);
            gameOverPaper= BitmapFactory.decodeResource(getResources(),R.drawable.dashtillpuffgameover,options);
            startMenu = BitmapFactory.decodeResource(getResources(),R.drawable.dashtillpuffstartmenu,options);
            rocket = new Rocket(this, Width, Height);
            trajectory = new Trajectory(Width, Height);
            cosmicFactory = new CosmicFactory(this, trajectory, Width, Height);
            renderThread = new DashTillPuffRenderThread ( this ) ;
            renderThread . start () ;
            paint = new Paint();
            paint.setAlpha(255);
            dst = new Rect(0, 0, getWidth(), getHeight());

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
                    if(!start && !gameOver ){   /*at the start menu*/
                        start = true;
                        rocket = new Rocket(this, Width, Height);
                        trajectory = new Trajectory(Width, Height);
                        cosmicFactory = new CosmicFactory(this, trajectory, Width, Height);
                    }
                    if(!gameOver && start)    /*playing the game*/
                        rocket.changeInputToTrue();
                    if(gameOver){
                        gameOver = false;
                        start = false;
                    }
                    break ;
                case MotionEvent . ACTION_UP : // Let space ship fall freely .
                    rocket.changeInputToFalse();  /*rocket going down*/
                    break;

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
            if(!gameOver||!start){                          /*start menu condition*/
                c.drawBitmap(startMenu, null, dst, paint);
            }
            if(!gameOver && start) {
                c.drawBitmap(bitmap, null, dst, paint);    /*background wallpaper*/
                //bitmap declared earlier to be the background pic.
                trajectory.tick(c);
                cosmicFactory.tick(c);
                rocket.tick(c);
                gameOver = rocket.collisionCheck(cosmicFactory);
            }
            if(gameOver){                                 /*game over condition*/
                start = false;
                c.drawBitmap(gameOverPaper, null, dst, paint);
            }
        }
    }


