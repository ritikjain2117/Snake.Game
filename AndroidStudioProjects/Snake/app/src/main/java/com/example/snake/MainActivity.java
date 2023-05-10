package com.example.snake;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 public SharedPreferences sharedPref;
 public int first;
 public int second;
 public int third;
 public TextView txtFirst;
 public TextView txtSecond;
 public TextView txtThird;

/*
* starter menù

* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        //tutte le impostazioni del gioco fanno riferimento alla preferenza globale Setting
        first = sharedPref.getInt("first",0);
        second = sharedPref.getInt("second",0);
        third = sharedPref.getInt("third",0);
        txtFirst = findViewById(R.id.txtFirst);
        txtSecond = findViewById(R.id.txtSecond);
        txtThird = findViewById(R.id.txtThird);
        txtFirst.setText("First Place : "+first);
        txtSecond.setText("Second Place : "+second);
        txtThird.setText("Third Plae : "+third);

    }

    public void start(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);

    }
    public void exit(View view ){

        MainActivity.this.finish();

    }
    public void Setting(View view){

        Intent intent = new Intent(this,Setting.class);
        startActivity(intent);

    }
}
