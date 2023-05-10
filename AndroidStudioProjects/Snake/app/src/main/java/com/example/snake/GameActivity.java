package com.example.snake;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

//========>>CONTROLLER<<========
public class GameActivity extends AppCompatActivity {

private float height;
private float width;
private Body body;
private GraphicsSnake graphicsSnake;
private TextView txtScore;
private  RelativeLayout relativeLayout;
private CountDown Counter;
private Count StarterCounter;
private SharedPreferences sharedPref ;
private SharedPreferences.Editor editor ;
private MediaPlayer sound;
private MediaPlayer lose;
private MediaPlayer bit;
private TextView txtCountDown;
private ProgressBar prgBar;
private int first;
private int second;
private int third;
private float Xstart;
private float Ystart;
private float Xstop;
private float Ystop;
private int count =3;
private int Difficult;
private boolean flag = false;
private boolean Effect,Music;
private int bodyTexture,map,headTexture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//imposta l'activity in full screen
        setContentView(R.layout.activity_game);

        //=======>>Game Setting
        sharedPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        bodyTexture = sharedPref.getInt("Body",0);
        map = sharedPref.getInt("map",1);
        headTexture = sharedPref.getInt("Head",0);
        Difficult = sharedPref.getInt("Difficult",100);
        Music = sharedPref.getBoolean("Music",false);
        Effect = sharedPref.getBoolean("Effect",false);
        first = sharedPref.getInt("first",0);
        second =  sharedPref.getInt("second",0);
        third = sharedPref.getInt("third",0);
        //==========================================================

        //======>SOUND
        sound = MediaPlayer.create(this,R.raw.sound);
        sound.setLooping(true);
        lose = MediaPlayer.create(this,R.raw.lose);
        lose.setLooping(false);
        bit = MediaPlayer.create(this,R.raw.bit);
        bit.setLooping(false);
        if(Music){
            sound.start();
        }
        //=========================
        //===>Generate Map and draws it
        height = 0;
        width = 0;
        getDimension(); //get dimension of the screen

        relativeLayout = findViewById(R.id.relativeLayout); //relative layout of the application
        body = new Body(this); //create body object


        graphicsSnake = new GraphicsSnake(relativeLayout, width, height, this, body,bodyTexture,headTexture);

        body.GenerateMap(map); //Generate map according to the type choosen by the user

        graphicsSnake.DrawMap(); //draw map
        //=====>Timer

        //Timer that dictate the time of application
        StarterCounter = new Count(3015,900);
        StarterCounter.start();
        //////////////////

    }

        public boolean onTouchEvent(MotionEvent e) {
        //using the swipe for the iteration with the user
        int lastMov = 1;
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                Xstart = e.getX();
                Ystart = e.getY();

            } else if (e.getAction() == MotionEvent.ACTION_UP) {
                Xstop = e.getX();
                Ystop = e.getY();


                lastMov = body.getLastMovement();
                firstMov = body.getLastMovement();

                if (Xstart + 100 < Xstop) {


                    if (lastMov != 2) {
                    body.setLastMovement(1);}

                } else if (Xstop < Xstart - 100) {
                    if (lastMov!= 1){
                    body.setLastMovement(2);}
                }
                if (Ystart + 100 < Ystop) {
                    if(lastMov != 3) {
                        body.setLastMovement(4);
                    }
                } else if (Ystop < Ystart - 100) {
                    if (lastMov != 4){
                    body.setLastMovement(3);}
                }
            }


            return true;
        }

public void bitsound(){if(Effect){bit.start();}}

public void Move(){ //called by onTick()

    body.moveGame();
    graphicsSnake.DrawMap();
    txtScore=findViewById(R.id.txtScore);
    txtScore.setTextColor(Color.RED);
    txtScore.setText("Score : " + (10*(body.getHead()-3)));

}

public void getDimension() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    width = size.x;
    height = size.y;
}
public void gameOver(){


   Counter.cancel();
   sound.stop();
   if(Effect){
       lose.start();
   }
    int score = (10*(body.getHead()-3));

   editor = sharedPref.edit();
   //Update Record
    if(score>first) {
        editor.putInt("second",sharedPref.getInt("first",0));
        editor.putInt("first", score);
    }else if(score>second){
        editor.putInt("third",sharedPref.getInt("second",0));
        editor.putInt("second",score);
    }else if(score>third){
        editor.putInt("third",score);
    }

    editor.apply();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(false);
    builder.setTitle("Game Over");
    builder.setIcon(R.drawable.icon);
    builder.setMessage(txtScore.getText());
    final GameActivity game2 = this;
    builder.setPositiveButton("Back Home",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(game2,MainActivity.class);
                    startActivity(intent);
                    sound.stop();
                    finish();
                }
            });
    builder.setNegativeButton("Restart Game",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                    Intent intent = new Intent(game2,GameActivity.class);
                    startActivity(intent);
                }
            });

    AlertDialog dialog = builder.create();
    dialog.show();








}
    public class CountDown extends CountDownTimer {

        CountDown(long millisinfuture, long countdowninterval) {
            super(millisinfuture, countdowninterval);

        }
        @Override
        public void onTick(long millisecondsUntilFinished) {
            Move();
        } //dictat the time of the game

        @Override
        public void onFinish(){
            Counter.start();
        }

    }


  //starter animation
    public class Count extends CountDownTimer {

        Count(long millisinfuture, long countdowninterval) {
            super(millisinfuture, countdowninterval);



        }
        @Override
        public void onTick(long millisecondsUntilFinished) {

                txtCountDown=findViewById(R.id.txtCount);
                //Progress bar
                if (count != 0) {
                    count--;
                    txtCountDown.setText("-" + count);
                } else {
                    txtCountDown.setTextSize(25);
                    txtCountDown.setText("GO");
                }


        }

        @Override
        public void onFinish(){
           //end of starter animation
            txtCountDown = findViewById(R.id.txtCount);
            prgBar = findViewById(R.id.prgBar);
            txtCountDown.setVisibility(android.view.View.INVISIBLE);
            prgBar.setVisibility(android.view.View.INVISIBLE);
            Counter.start();
        }
    }

    @Override
    public void onBackPressed() {
        Counter.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Pause");
        builder.setIcon(R.drawable.apple);
        builder.setMessage("Game in pause");
        final GameActivity game2 = this;
        builder.setPositiveButton("Back Home",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(game2,MainActivity.class);
                        startActivity(intent);
                        sound.stop();
                        finish();
                    }
                });
        builder.setNegativeButton("Resume Game",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    Counter.start();
                    dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}






