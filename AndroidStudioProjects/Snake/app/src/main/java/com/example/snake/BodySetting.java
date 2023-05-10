package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class BodySetting extends AppCompatActivity {
    private float Xstart;
    private float Xstop;
    private float Ystart;
    private float Ystop;
    private int count;
    private ImageView imgBody;
    private int img[] = new int[4];
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_setting);
        imgBody = findViewById(R.id.imgBody);


        img[0] = R.drawable.snakebodytexture1;

        img[1] = R.drawable.snakebodytexture2;

        img[2] = R.drawable.snakebodytexture3;

        img[3] = R.drawable.snakebodytexture4;

        imgBody.setImageResource(img[0]);

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

                //swipe verso destra
                if(count!=3) {
                    count++;
                    imgBody.setImageResource(img[count]);
                }else{
                    count = 0;
                    imgBody.setImageResource(img[count]);
                }
            }else if(Xstart - 100 > Xstop) {

                //swipe verso sinistra
                if(count != 0){
                    count--;
                    imgBody.setImageResource(img[count]);
                }else{
                    count = 3;
                    imgBody.setImageResource(img[count]);
                }

            }}
        return true;
    }
    public void change(View view){
        //registro la texture scelta dal giocatore e chiudo l'activity corrente tornanto all'activity Setting
        Intent intent = new Intent(this,Setting.class);
        editor = sharedPref.edit();
        editor.putInt("Body",count);
        editor.apply();

        startActivity(intent);
        finish();


    }
}
