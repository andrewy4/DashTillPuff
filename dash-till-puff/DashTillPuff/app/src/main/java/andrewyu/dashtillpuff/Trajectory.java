package andrewyu.dashtillpuff;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Chau on 4/29/2015.
 */

    public class Trajectory implements TimeConscious {

        Line p = new Line();

        private void draw( Canvas c){
            Path path = new Path ();
            path . moveTo ( ) ; // Move to first point
            for ( int i = 1; i < points.size() ; ++ i ) {

                path . lineTo ( p .x , p . y ) ;
            }
            Paint paint = new Paint () ;

// Set paint color , alpha , line width , dashed style , etc .

            c . drawPath ( path , paint ) ;


        }


        private ArrayList<PointF> points ;

        @Override
        public void tick ( Canvas canvas ) {




            System.out.println("hello");

// As time ticks , append more points to the trajectory and
// discard those points that have crossed the entire
// span of the screen .

            draw(canvas) ;
        }
}
