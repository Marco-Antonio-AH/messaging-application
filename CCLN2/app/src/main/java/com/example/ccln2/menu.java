package com.example.ccln2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class menu extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(menu.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 3000);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3:
                Intent intent = new Intent(menu.this, MainActivity.class);
                startActivity(intent);
                System.out.println("Pasó por el menu");

        }
    }

    public void sala(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Pasó por el onClick");
    }

}