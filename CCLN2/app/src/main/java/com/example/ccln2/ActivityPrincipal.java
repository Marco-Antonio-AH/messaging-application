package com.example.ccln2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPrincipal extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                System.out.println("Pasó por el activityPrincipal");

        }
    }

    public void sala(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Pasó por el onClick");
    }
}