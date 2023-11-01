package com.example.fivesongsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mediaplayer;
    int count_pause = 0 ;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_1 = findViewById(R.id.button1);
        button_2 = findViewById(R.id.button2);
        button_3 = findViewById(R.id.button3);
        button_4 = findViewById(R.id.button4);
        button_5 = findViewById(R.id.button5);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
    }

    public void song(int song){
        if(mediaplayer == null){
            mediaplayer = MediaPlayer.create(this,song);
            mediaplayer.start();
        }
        else{
            mediaplayer.release();
            mediaplayer = MediaPlayer.create(this,song);
            mediaplayer.start();
        }
        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pleaseStop();
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pleaseStop();
    }

    public void pause(View v){
        if(mediaplayer != null && count_pause == 0){
            mediaplayer.pause();
            count_pause += 1;
            Toast.makeText(this, "Song Paused", Toast.LENGTH_SHORT).show();
        }
        else if(mediaplayer != null && count_pause == 1){
            mediaplayer.start();
            count_pause -= 1;
        }
    }

    public void reset(View v){
        if(mediaplayer != null){
            mediaplayer.seekTo(0);
            mediaplayer.start();
            Toast.makeText(this, "Song Reset", Toast.LENGTH_SHORT).show();
        }
    }
    public void stop(View v){
        pleaseStop();
    }
    private void pleaseStop(){
        if(mediaplayer != null){
            mediaplayer.release();
            mediaplayer = null;
            Toast.makeText(this, "Song Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1) {
            song(R.raw.lol);
        } else if (id == R.id.button2) {
            song(R.raw.dancin);
        } else if (id == R.id.button3) {
            song(R.raw.im_good);
        } else if (id == R.id.button4) {
            song(R.raw.murder);
        } else if (id == R.id.button5) {
            song(R.raw.revelca_song);
        }
    }
}