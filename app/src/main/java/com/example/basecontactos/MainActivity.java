package com.example.basecontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button boton_pulsame;
    ProgressBar progressBar;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton_pulsame = findViewById(R.id.boton_Pulsame);
        progressBar = findViewById(R.id.progressBar);

        boton_pulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        progressBar.setProgress(counter);
                        if (counter == 40) {
                            timer.cancel();
                            Intent intent = new Intent(MainActivity.this,MainMenu.class);
                            startActivity(intent);
                        }
                    }
                };
                timer.schedule(timerTask, 40, 40);
            }
        });


    }







}