package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class Setting extends AppCompatActivity {
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor ;
    Switch swtMusic;
    Switch swtEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        boolean modeMusic = sharedPref.getBoolean("Music",false);
        boolean modeEffect = sharedPref.getBoolean("Effect",false);
        swtMusic = findViewById(R.id.swtMusica);
        swtEffect = findViewById(R.id.swtEffettiSonori);
        swtMusic.setChecked(modeMusic);
        swtEffect.setChecked(modeEffect);
    }
    public void head(View view){
        Intent intent = new Intent(this,HeadSetting.class);
        startActivity(intent);

    }
    public void body(View view){
        Intent intent = new Intent(this, BodySetting.class);
        startActivity(intent);

    }
    public void map(View view){
        Intent intent = new Intent(this,MapSetting.class);
        startActivity(intent);
    }
    public void Musica1_Mode(View view){
        editor = sharedPref.edit();
        if(!swtMusic.isChecked()){
            editor.putBoolean("Music",false);

        }else{
            editor.putBoolean("Music",true);
        }
        editor.apply();
    }
    public void Effect_Mode(View view){
        editor = sharedPref.edit();
        if(swtEffect.isChecked()){
            editor.putBoolean("Effect",true);
        }else{
            editor.putBoolean("Effect",false);
        }
        editor.apply();
    }


    public void Exit(View view){

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();


        }
        //difficult depend on the period of onTick method
    public void Easy(View view){
        editor = sharedPref.edit();
        editor.putInt("Difficult",130);
        editor.apply();
    }
    public void Normal(View view){
        editor = sharedPref.edit();
        editor.putInt("Difficult",100);
        editor.apply();
    }
    public void Hard(View view){
        editor = sharedPref.edit();
        editor.putInt("Difficult",70);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
       finish();
    }

}
