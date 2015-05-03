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
         private float x;
         private float y;
         private Path path;
         private Paint paint;

         Trajectory(float Width, float Height){
             path = new Path ();
             paint = new Paint();
             paint.setStyle(Paint.Style.STROKE);
             paint.setStrokeWidth(10);
             y = Height/2; // drawing the line from the beginning
             x= 0;// starting in the middle

             int i=0; // initialize counter

             // This do while loop will determine the points
             do{
                 points.add(i, new PointF(x, y));
                 x += Width/40;
                 i++;
             }
             while(i<41);
         }

         private void update(){

         

         }


         @Override
        public void tick ( Canvas canvas ) {

             // As time ticks , append more points to the trajectory and
            // discard those points that have crossed the entire
            // span of the screen .


             // Set paint color , alpha , line width , dashed style , etc .

             // This for loop will connect the said points;

            for(int i = 1; i<41; i+=2){
                path.moveTo(points.get(i-1).x, points.get(i-1).y);
                path.lineTo(points.get(i).x, points.get(i).y);
                paint.setColor(Color.WHITE);
                canvas . drawPath ( path , paint ) ;


               // points.set(i, points.get(i)); // d

             }

             //as this function is called it with keep redrawing the

        }
}
