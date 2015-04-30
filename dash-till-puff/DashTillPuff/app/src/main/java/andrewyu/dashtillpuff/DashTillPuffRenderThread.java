package andrewyu.dashtillpuff;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Chau on 4/29/2015.
 */

public class DashTillPuffRenderThread extends Thread {
    private final DashTillPuffSurfaceView view ;
    private DashTillPuffSurfaceView bsv;
    private static final int FRAME_PERIOD = 5; // In ms
    public DashTillPuffRenderThread ( DashTillPuffSurfaceView view ) {
        this . view = view ;
    }
    public void run () {
        SurfaceHolder sh = bsv . getHolder () ;
// Main game loop .
        while ( ! Thread . interrupted () ) {
            Canvas c = sh . lockCanvas ( null ) ;
            try {
                synchronized ( sh ) {
                    bsv . tick ( c ) ;
                }
            } catch ( Exception e ) {
            } finally {
                if ( c != null ) {
                    sh . unlockCanvasAndPost ( c ) ;
                }
            }
            try {
                Thread . sleep ( FRAME_PERIOD ) ;
            } catch ( InterruptedException e ) {
                return ;
            }
        }
    }
}