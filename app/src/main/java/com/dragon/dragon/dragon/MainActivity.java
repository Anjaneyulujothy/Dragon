package com.dragon.dragon.dragon;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private TextView start,score;

    private ImageView red,blue,bug,dragon;

    private  int boxY;


    private Handler handler= new Handler();

    private Timer timer=new Timer();

    private boolean action_flag=false;
    private boolean start_flag=false;


    private  int frameHeight;

    private  int boxSize;


    private  int screenWidth;

    private int screenHeight;

    private  int redX;
    private  int redY;


    private  int blueX;
    private  int blueY;



    private  int bugX;
    private  int bugY;


    private  int scor =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start=(TextView)findViewById(R.id.start);
        score=(TextView)findViewById(R.id.scorelable);

        red=(ImageView)findViewById(R.id.red);
        blue=(ImageView)findViewById(R.id.blue);
        bug=(ImageView)findViewById(R.id.bug);
        dragon=(ImageView)findViewById(R.id.dragon);



        WindowManager windowManager= getWindowManager();

        Display desplay=windowManager.getDefaultDisplay();

        Point size= new Point();
        desplay.getSize(size);

        screenHeight=size.y;
        screenWidth=size.x;

        red.setX(-(80));
        red.setY(-(80));

        blue.setX(-(80));
        blue.setY(-(80));

        bug.setX(-(80));
        bug.setY(-(80));




        boxY =500;

        score.setText("score : 0" );

    }

    public void changePos()

    {


        hitCheck();

        redX -= 12;
        if(redX <0)
        {

            redX =screenWidth +20;

            redY=(int)Math.floor(Math.random() *(frameHeight - red.getHeight()));
        }

        red.setX(redX);

        red.setY(redY);

        bugX -= 16;
        if(bugX <0)
        {

            bugX =screenWidth +10;

            bugY=(int)Math.floor(Math.random() *(frameHeight - bug.getHeight()));
        }

        bug.setX(bugX);

        bug.setY(bugY);


        blueX -= 20;
        if(blueX <0)
        {

           blueX =screenWidth +5000;

            blueY=(int)Math.floor(Math.random() *(frameHeight - blue.getHeight()));
        }

        blue.setX(blueX);

        blue.setY(blueY);



        if(action_flag==true)
        {

            boxY -=20;
        }

        else {

            boxY +=20;
        }

        if(boxY<0)
            boxY=0;

        if(boxY> frameHeight -boxSize)
            boxY = frameHeight-boxSize;
        dragon.setY(boxY);

        score.setText("score :" +scor);

    }

    public void hitCheck()
    {


        int redCenterX=redX +red.getWidth()/2;

        int redCenterY=redY +red.getHeight()/2;

        if(0<=redCenterX && redCenterX<= boxSize && boxY<=redCenterY && redCenterY <= boxY +boxSize)
        {
            scor +=10;
            redX -=10;
        }


        int blueCenterX=blueX +blue.getWidth()/2;

        int blueCenterY=blueY +blue.getHeight()/2;

        if(0<=blueCenterX && blueCenterX<= boxSize && boxY<=blueCenterY && blueCenterY <= boxY +boxSize)
        {
            scor +=30;
            blueX -=10;
        }



        int bugCenterX=bugX +bug.getWidth()/2;

        int bugCenterY=bugY +bug.getHeight()/2;

        if(0<=bugCenterX && bugCenterX<= boxSize && boxY<=bugCenterY && bugCenterY <= boxY +boxSize)
        {
           timer.cancel();
            timer=null;

            Intent i=new Intent(MainActivity.this,Result.class);

            i.putExtra("score",scor);

            startActivity(i);
        }
    }


    public  boolean onTouchEvent(MotionEvent me)
    {


        if(start_flag==false)
        {
           start_flag=true;


            FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame);
            frameHeight=frameLayout.getHeight();

            boxY=(int)dragon.getY();
            boxSize=dragon.getHeight();

            start.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changePos();

                        }
                    });
                }
            },0,20);


        }
        else {
            if(me.getAction() == MotionEvent.ACTION_DOWN)
            {

                action_flag=true;

            }
            else  if(me.getAction() == MotionEvent.ACTION_UP)
            {

                action_flag=false;
            }

        }


        return true;
    }
}
