/*This thing need to be changed completely. Testing for now*/
package andrewyu.dashtillpuff;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.*;
import android.content.Context;

import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.*;


     class Trajectory implements TimeConscious {
         ArrayList<PointF> points = new ArrayList<>();
/*
         public void pseudoMethod(){
            // Choosing these points
             points.add(0,PointF(1,3));
             points.add(1,);
             points.add(2,);
             points.add(3,);
             points.add(4,);
         }
*/
         /*
        public void draw( Canvas c, float Width, float Height){
            // this thing should draw the line
            Path path = new Path ();
           // path . moveTo ( Width/2, Height) ; // Move to first point
            path . lineTo (Width/2, Height/2);
            Paint paint = new Paint();
// Set paint color , alpha , line width , dashed style , etc .
            paint.setColor(Color.BLACK);
            c . drawPath ( path , paint ) ;
        }
        */

         @Override
        public void tick ( Canvas canvas ) {

             // As time ticks , append more points to the trajectory and
            // discard those points that have crossed the entire
            // span of the screen .
             Path path = new Path ();


             // path . moveTo ( Width/2, Height) ; // Move to first point

             path . lineTo (canvas.getWidth()/2, canvas.getHeight()/2);
             Paint paint = new Paint();
             paint.setStyle(Paint.Style.STROKE);
             paint.setStrokeWidth(10);
            // Set paint color , alpha , line width , dashed style , etc .
             paint.setColor(Color.WHITE);
             canvas . drawPath ( path , paint ) ;

        }
}
