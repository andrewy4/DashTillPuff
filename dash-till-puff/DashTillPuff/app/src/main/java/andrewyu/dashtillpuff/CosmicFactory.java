package andrewyu.dashtillpuff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Random;



/*
 * Created by Chau on 4/29/2015.
 */
public class CosmicFactory implements TimeConscious{
    private int counter;
    private int bit;
    private float Height;
    private float Width;
    private Trajectory t;
    ArrayList<Cluster> cluster = new ArrayList<>();
    ArrayList<Bitmap> bitmap = new ArrayList<>();
    private boolean changePic;


    CosmicFactory(DashTillPuffSurfaceView view,Trajectory t, float Width, float Height){
        counter = 0;
        changePic = true;
        this.t = t;
        this.Height = Height;
        this.Width = Width;
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffblackhole, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffblueplanet, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffcloud, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffearth, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffglossyplanet, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffblueplanet, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffgoldenstar, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffneutronstar, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffstar1, options));
        bitmap.add(BitmapFactory.decodeResource(view.getResources(), R.drawable.dashtillpuffstar2, options));


    }

    public void drawCluster(Canvas c){

        for(int i = 1; i< cluster.size();i++)
        c.drawBitmap(bitmap.get(cluster.get(i).returnBit()), null, cluster.get(i).returnDst(), cluster.get(i).returnPaint());
        counter -=1;
    }
    public void removeCosmic() {
        for (int i = 0; i < cluster.size(); i++) {
            if (cluster.get(i).returnX2() < 0)
                cluster.remove(i);
        }
    }

    public void counterChange(){
        Random ran = new Random();
        counter = ran.nextInt(10)+1;
    }

    public void changePic(){
        int changeStyle = 0;
        for(int i = cluster.size()-11;i<cluster.size();i++){
            if(i>1){
                if(cluster.get(i).returnBit() == cluster.get(i-1).returnBit() ){
                    changeStyle +=1;
                }
            }
        }
        if(changeStyle == 9){
            changePic = true;
        }

            if(changePic){
                int temp;
                Random ran = new Random();
                temp = ran.nextInt(10);
                while(temp == bit){
                    temp = ran.nextInt(10);
                }
                bit = temp;

                changePic = false;
            }
    }

    @Override
    public void tick ( Canvas canvas ) {

        if(counter <=0) {
            cluster.add(new Cluster(t, Height, bit));
            counterChange();
        }
        for(int i = 0; i<cluster.size();i++)
            cluster.get(i).updateRect(Width);
        removeCosmic();
        changePic();
        drawCluster(canvas);
        counter -=1;
    }

}
