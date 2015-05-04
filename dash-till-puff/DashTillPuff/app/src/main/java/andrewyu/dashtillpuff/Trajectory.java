package andrewyu.dashtillpuff;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.*;



     class Trajectory implements TimeConscious {
         ArrayList<PointF> points = new ArrayList<>();

         private Paint paint;
         private float Height;
         private int counter;
         private Random rand;
         private int shift;


         Trajectory(float Width, float Height){
             shift = 0;
             rand = new Random();
             counter  = 0;
             this.Height = Height;
             paint = new Paint();
             paint.setStyle(Paint.Style.STROKE);
             paint.setStrokeWidth(10);
             float y = Height/2; // drawing the line from the beginning
             float x= 0;// starting in the middle

             int i=0; // initialize counter
             // This do while loop will determine the points
             do{
                 points.add(i, new PointF(x, y));
                 x += Width/40;
                 i++;
             }
             while(i<41);
         }

         public void updateLine(){

             for(int i = 0; i<40; i++)                    //shifting the y depends on the previous y
                 points.get(i).y = points.get(i+1).y;
             if(counter == 10 ) {                         //counter makes the line going in one direction at a time
                 shift = (rand.nextInt(75) - 37);
                 counter = 0;
             }
             points.get(40).y += shift;
             counter +=1;
             while(points.get(40).y<0 || points.get(40).y> Height){
                 shift = rand.nextInt(75)-37;
                 points.get(40).y+= shift;
                 counter = 1;
             }

         }

         private void lineDraw(Canvas canvas){
             Path path = new Path();
             for(int i = 1; i<41; i+=2) {
                 path.moveTo(points.get(i - 1).x, points.get(i - 1).y);
                 path.lineTo(points.get(i).x, points.get(i).y);
                 paint.setColor(Color.WHITE);
                 canvas.drawPath(path, paint);
             }
         }

         @Override
        public void tick ( Canvas canvas ) {
             updateLine();
             lineDraw(canvas);
        }
}
