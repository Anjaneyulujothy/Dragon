package com.dragon.dragon.dragon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView rs,rhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        rs=(TextView)findViewById(R.id.rscore);
        rhs=(TextView)findViewById(R.id.rhighscore);

        int score=getIntent().getIntExtra("score",0);

        rs.setText(""+score);

        SharedPreferences settings=getSharedPreferences("GAME-DATA", Context.MODE_PRIVATE);
      int hs=  settings.getInt("Hs",0);

        if(score>hs)
        {
            rhs.setText("HIGH SCORE :" +score);

            SharedPreferences.Editor  editor=settings.edit();

            editor.putInt("Hs",score);

            editor.commit();
        }
        else
        {

            rhs.setText("HIGH SCORE :" +hs);
        }

    }

    public void tryAgain(View view)
    {

startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}
