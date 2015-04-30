/*This thing need to be changed completely. Testing for now*/
package andrewyu.dashtillpuff;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;



     class Trajectory implements TimeConscious {



        public void draw( Canvas c, float Width, float Height){

            Path path = new Path ();
            path . moveTo ( Width/2, Height) ; // Move to first point
            path . lineTo (Width/2, 0);
            Paint paint = new Paint();
// Set paint color , alpha , line width , dashed style , etc .
            paint.setColor(Color.BLACK);
            c . drawPath ( path , paint ) ;


        }


        private ArrayList<PointF> points ;

        @Override
        public void tick ( Canvas canvas ) {

// As time ticks , append more points to the trajectory and
// discard those points that have crossed the entire
// span of the screen .


        }
}
