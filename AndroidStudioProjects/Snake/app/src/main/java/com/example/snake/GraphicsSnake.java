package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;

//view
public class GraphicsSnake {
    private float pxwidthBlock;
    private float pxheightBlock;
    private Body model;
    private ImageView block[][];
    private int bodyTexture1;
    private int HeadLeft,HeadRight,HeadUp,HeadDown;
    private int BodySnake;
    private boolean firstTime=true;


    public GraphicsSnake(RelativeLayout relativeLayout, float width, float height, GameActivity activity, Body model2, int bodyTexture, int headtexture) { //costruttore

        //x=27xy=48


        //choose the texture according with the user
        switch(headtexture){
            case 0:
                HeadRight = R.drawable.snakeheadtextureright1;

                HeadLeft = R.drawable.snakeheadtextureleft1;

                HeadUp = R.drawable.snakeheadtextureup1;

                HeadDown = R.drawable.snakeheadtexturedown1;
                break;
            case 1:
                HeadRight = R.drawable.snakeheadtextureright2;

                HeadLeft = R.drawable.snakeheadtextureleft2;

                HeadUp = R.drawable.snakeheadtextureup2;

                HeadDown = R.drawable.snakeheadtexturedown2;
                break;
            case 2:
                HeadRight = R.drawable.snakeheadtextureright3;

                HeadLeft = R.drawable.snakeheadtextureleft3;

                HeadUp = R.drawable.snakeheadtextureup3;

                HeadDown = R.drawable.snakeheadtexturedown3;

                break;
            case 3:
                HeadRight = R.drawable.snakeheadtextureright4;

                HeadLeft = R.drawable.snakeheadtextureleft4;

                HeadUp = R.drawable.snakeheadtextureup4;

                HeadDown = R.drawable.snakeheadtexturedown4;

                break;
        }

        //choose body of snake
        switch(bodyTexture){
            case 0:
                BodySnake = R.drawable.snakebodytexture1;
                break;
            case 1:
                BodySnake = R.drawable.snakebodytexture2;
                break;
            case 2:
                BodySnake = R.drawable.snakebodytexture3;
                break;
            case 3:
                BodySnake = R.drawable.snakebodytexture4;
                break;
        }

        block = new ImageView[48][27];
        pxwidthBlock = width / 27;
        pxheightBlock = height / 48;
        RelativeLayout.LayoutParams params;
        model = model2; //Body
        bodyTexture1  = bodyTexture;

        //divide the screen in ImageView
        for (int i = 0; i <= 47; i++) {
            for (int j = 0; j <= 26; j++) {

                params = new RelativeLayout.LayoutParams((int) (pxwidthBlock), (int) (pxheightBlock) );//size imageView
                block[i][j] = new ImageView(activity); //activity = contentx of activity
                params.leftMargin = Posizione(j, width, true); //
                params.topMargin =  Posizione(i, height, false);
                relativeLayout.addView(block[i][j], params);
            }
        }

    }

    public int Posizione(int k, float dimension, boolean d) {
        //calculate the position of the imageView, k is row or column
        if (d) {//se leftMargin
            return ((int) (dimension / 27) * k);
        } else {//se topMargin
            return ((int) (dimension / 48) * k);
        }
    }

    //draws the map according to the body
    public void DrawMap() {

        if(firstTime){
            block[23][14].setImageResource(HeadRight);

        }

        for (int row = 0; row <= 47; row++) {
            for (int clm = 0; clm <= 26; clm++) {
                if (model.showBody(row, clm) == -1) {
                    block[row][clm].setImageResource(R.drawable.block);

                }
                if (model.showBody(row, clm) >= 1) {
                    if (model.showBody(row, clm) == model.getHead()) {
                        switch (model.getLastMovement()) {
                            case 1:
                                block[row][clm].setImageResource(HeadRight);

                                break;
                            case 2:
                                block[row][clm].setImageResource(HeadLeft);

                                break;
                            case 3:
                                block[row][clm].setImageResource(HeadUp);

                                break;

                            case 4:
                                block[row][clm].setImageResource(HeadDown);

                                break;

                        }//endSWITCH
                    } else {
                        block[row][clm].setImageResource(BodySnake);

                    }
                }
                if (model.showBody(row, clm) == 0) {
                    block[row][clm].setImageResource(0);
                }
                if (model.showBody(row, clm) == -4) {
                    block[row][clm].setImageResource(R.drawable.apple);
                }
                if(model.showBody(row,clm) == -5){
                    block[row][clm].setImageResource(R.drawable.poison);
                }

            }
        }


    }
}
