package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MapSetting extends AppCompatActivity {
    private float Xstart;
    private float Xstop;
    private float Ystart;
    private float Ystop;
    private int count;
    private ImageView imgMap;
    private int img[] = new int[4];
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_setting);
        imgMap = findViewById(R.id.imgMap);


        img[0] = R.drawable.map0;

        img[1] = R.drawable.map1;

        img[2] = R.drawable.map2;
        imgMap.setImageResource(img[0]);



        sharedPref = getSharedPreferences("Setting", Context.MODE_PRIVATE); //Contenitore di variabili in cui ci stanno tutte le setting

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
                if(count!=2) {
                    count++;
                    imgMap.setImageResource(img[count]);
                }else{
                    count = 0;
                    imgMap.setImageResource(img[count]);
                }
            }else if(Xstart - 100 > Xstop) {

                //left
                if(count != 0){
                    count--;
                    imgMap.setImageResource(img[count]);
                }else{
                    count = 2;
                    imgMap.setImageResource(img[count]);
                }

            }}
        return true;
    }
    public void change(View view){

        Intent intent = new Intent(this,Setting.class);
        editor = sharedPref.edit();
        editor.putInt("map",count);
        editor.apply();

        startActivity(intent);
        finish();

    }
}
