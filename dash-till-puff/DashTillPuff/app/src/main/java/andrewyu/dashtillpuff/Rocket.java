package andrewyu.dashtillpuff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/*
 * Created by Andrew Yu on 5/3/2015.
 */
public class Rocket implements TimeConscious{
    private Bitmap bitmapUp;
    private Bitmap bitmapDown;
    private int x1, y1, x2, y2;
    private Rect dst;
    private Paint rocketPaint;
    public boolean input;
    private float Height;

    Rocket(DashTillPuffSurfaceView view, float Width, float Height){
        this.Height = Height;
        rocketPaint = new Paint();
        rocketPaint.setAlpha(255);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmapUp = BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffspaceship, options);
        bitmapDown = BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffspaceship2, options);
        x1 = (int)Width/2-75;
        x2 = (int)Width/2+75;
        y1 = (int)Height/2-75;
        y2 = (int)Height/2+75;
        dst = new Rect(x1,y1,x2,y2);
        input = false;
    }
    void changeInputToTrue(){
        input = true;
    }
    void changeInputToFalse(){
        input = false;
    }
    public void acceleration(float Height) {   //spaceship going up and down
        if (input) {
            y1 -= 30;
            y2 -= 30;
            if (y1 < 0) {
                y1 = 0;
                y2 = 150;
            }
        }
        if (!input) {
            y1 += 30;
            y2 += 30;
            if (y1 > Height) {
                y1 = (int) Height - 150;
                y2 = (int) Height;
            }
        }
        dst = new Rect(x1, y1, x2, y2);
    }
    public boolean collisionCheck(CosmicFactory cF){
        float centerX = dst.exactCenterX();
        float centerY = dst.exactCenterY();
        for(int i =0;i<cF.cluster.size();i++) {
            if (cF.cluster.get(i).returnX1() <= centerX && centerX <= cF.cluster.get(i).returnX2() && cF.cluster.get(i).returnY1()<=centerY && centerY <= cF.cluster.get(i).returnY2())
                return true;
        }
        return false;
    }


    @Override
    public void tick(Canvas canvas){
        acceleration(Height);
        if(!input)
            canvas.drawBitmap(bitmapDown, null, dst,rocketPaint);
        if(input)
            canvas.drawBitmap(bitmapUp, null, dst,rocketPaint);
    }




}
