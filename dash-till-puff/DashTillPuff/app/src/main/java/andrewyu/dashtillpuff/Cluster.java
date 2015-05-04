package andrewyu.dashtillpuff;

import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/*
 * Created by Ohyehyu on 5/3/2015.
 */
public class Cluster {
        private Paint clusterPaint;
        private Rect dst;
        private int x1;
        private int y1;
        private int diff;
        private int bit;
    public int returnX2(){return x1+diff;}
    public int returnY2(){return y1+diff;}
    public int returnX1(){return x1;}
    public int returnY1(){return y1;}
    public Rect returnDst(){return dst;}
    public Paint returnPaint(){return clusterPaint;}
    public int returnBit(){return bit;}
    Cluster(Trajectory t, float Height, int bit){
        this.bit = bit;
        Random ran = new Random();
        clusterPaint = new Paint();
        clusterPaint.setAlpha(255);
        x1 = (int)t.points.get(40).x;
        if(t.points.get(40).y >= Height/2){
            y1 = ran.nextInt((int)t.points.get(40).y) -200;
            while(y1<0){
                y1 = ran.nextInt((int)t.points.get(40).y) -200;
            }
            diff = ran.nextInt(50)+100;
        }
        if(t.points.get(40).y<Height/2){
            y1 = ran.nextInt((int) (Height-t.points.get(40).y)+200);
            while(y1>Height){
                y1 = ran.nextInt((int) (Height-t.points.get(40).y)+200);
            }
            diff = ran.nextInt(50)+100;
        }
        dst = new Rect(x1,y1,x1+diff,y1+diff);
    }


    public void updateRect(float Width){
        x1 -=Width/40;
        dst = new Rect(x1,y1,x1+diff,y1+diff);
    }
}
