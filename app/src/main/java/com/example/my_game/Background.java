package com.example.my_game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;

public class Background {
    int x = 0 ,y = 0  ;
    Bitmap background ;
    Background (int screenX , int screenY , Resources res){

        background = BitmapFactory.decodeResource(res,R.drawable.baseballBackground);
        background = Bitmap.createScaledBitmap(background,screenX,screenY,false);


    }
}
