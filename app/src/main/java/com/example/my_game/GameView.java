package com.example.my_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread ;
    private boolean isPlaying ;
    private Background background1 , background2 ;
    public static float screenRatioX , screenRatioY ;
    private Paint paint ;
    private Spin spin ;
    private int screenX , screenY ;




    public GameView(Context context , int screenX , int screenY) {
        super(context);
        this.screenX = screenX ;
        this.screenY = screenY ;
        screenRatioX = 1920f / screenX ;
        screenRatioY = 1080f / screenY ;
        background1 = new Background (screenX,screenY,getResources());
        background2 = new Background (screenX,screenY,getResources());

        spin = new Spin(screenX,screenY,getResources()) ;

        background2.x = screenX ;
        paint = new Paint();



    }

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }
    float theta=0 , deltatheta=30 , ox=50*screenRatioX , oy=50*screenRatioY ;
    float calcx , calcy ,r=45*screenRatioY ;
    float y0 = 50 * screenRatioY ,  x0 = (100*screenRatioX-2*r)/2 + 2*r ;
    private void update (){
        background1.x -= 10 * screenRatioX ;
        background2.x -= 10 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0){
            background1.x = screenX ;
        }
        if (background2.x + background2.background.getWidth() < 0){
            background2.x = screenX ;
        }

        if (spin.isGoingLeft){
        }
            theta+= deltatheta ;
            if (theta>= 360)
                theta -=360 ;
            calcx = x0 + r * Math.cos(Math.toRadians(theta));
            calcy = y0 + r * Math.sin(Math.toRadians(theta));
            spin.x = ox + calcx;
            spin.y = oy - calcy;
            x0 = spin.x ;
            y0 = spin.y ;

        }
        else
        {
            theta -= deltatheta;
            if (theta <= 0)
                theta += 360;
            calcx = x0 + r * Math.cos(Math.toRadians(theta));
            calcy = y0 + r * Math.sin(Math.toRadians(theta))
            spin.x = ox + calcx;
            spin.y = oy - calcy;
            x0 = spin.x;
            y0 = spin.y;
        }
    }




    private void draw (){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas() ;
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

            canvas.drawBitmap(spin.getSpin(),spin.x,spin.y,paint);

            getHolder().unlockCanvasAndPost(canvas);
        }


    }
    private void sleep (){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume (){
        isPlaying = true ;
        thread = new Thread(this );
        thread.start();


    }
    public void pause (){
        try {
            isPlaying = false ;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true ;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX()<screenX/2){
                    spin.isGoingLeft = true ;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX()>screenX/2){
                    spin.isGoingLeft = false ;
                break;
        }
    }
}
