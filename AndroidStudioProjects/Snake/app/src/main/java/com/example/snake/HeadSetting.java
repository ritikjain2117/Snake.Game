package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class HeadSetting extends AppCompatActivity {
    private float Xstart;
    private float Xstop;
    private float Ystart;
    private float Ystop;
    private int count=0;
    private ImageView imgHead;
    private int img[] = new int[4];
    private SharedPreferences sharedPref ;
    private SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_setting);
        imgHead = findViewById(R.id.imgHead);
        sharedPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);


        img[0] = R.drawable.snakeheadtextureup1;

        img[1] = R.drawable.snakeheadtextureup2;

        img[2] = R.drawable.snakeheadtextureup3;

        img[3] = R.drawable.snakeheadtextureup4;


        imgHead.setImageResource(img[count]);
    }
    public boolean onTouchEvent(MotionEvent e) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            Xstart = e.getX();
            Ystart = e.getY();

        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            Xstop = e.getX();
            Ystop = e.getY();

            if(Xstart + 100 < Xstop) {

                //right
                if(count!=3) {
                    count++;
                    imgHead.setImageResource(img[count]);
                }else{
                    count = 0;
                    imgHead.setImageResource(img[count]);
                }
            }else if(Xstart - 100 > Xstop) {

                //left
            if(count != 0){
                count--;
                imgHead.setImageResource(img[count]);
            }else{
                count = 3;
                imgHead.setImageResource(img[count]);
            }

            }}
        return true;
        }
    public void change(View view){

        Intent intent = new Intent(this,Setting.class);
        editor = sharedPref.edit();
        editor.putInt("Head",count);
        editor.apply();

        startActivity(intent);
        finish();

    }

    }

