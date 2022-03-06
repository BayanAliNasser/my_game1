package com.example.my_game;

import static com.example.my_game.GameView.screenRatioX;
import static com.example.my_game.GameView.screenRatioY;

import android.content.res.Resources ;
import  android.graphics.Bitmap ;
import android.graphics.BitmapFactory;

public class Spin {


    boolean isGoingLeft=false  ;
    int width,height , hitcounter=0,theta=0,deltatheta=30;
    float x,y,r=45*screenRatioY,ox=50*screenRatioX,oy=50*screenRatioY;


    Bitmap bat1,bat2 , ball ;
    Spin (int screenX , int screenY , Resources res) {
        bat1 = BitmapFactory.decodeResource(res , R.drawable.bat1);
        bat2 = BitmapFactory.decodeResource(res , R.drawable.bat2);
        ball = BitmapFactory.decodeResource(res , R.drawable.ball);

        width = bat1.getWidth();
        height = bat1.getHeight();

        width /= 5;
        height /= 5;

        width *= (int) screenRatioX ;
        height *= (int) screenRatioY ;

        bat1 = Bitmap.createScaledBitmap(bat1,width,height,false);
        bat2 = Bitmap.createScaledBitmap(bat2,width,height,false);

        y = 50 * screenRatioY ;
        x = (100*screenRatioX-2*r)/2 + 2*r ;


    }
Bitmap getSpin (){

        if (hitcounter==0){
            hitcounter++;
            return bat1 ;
        }
        hitcounter -- ;
        return bat2 ;
}

}
