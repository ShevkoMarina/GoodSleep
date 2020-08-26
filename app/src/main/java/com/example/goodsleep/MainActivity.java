package com.example.goodsleep;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPlayer1, mPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<SliderItem> sliderItems = new ArrayList<>();

//        // Here we can upload images on server and receive JSON but I am a lazy bitch
//        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
//        sliderItems.add(new SliderItem(R.drawable.image1, "Forest"));
//        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
//        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
//        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
//        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));

        mPlayer1 = MediaPlayer.create(this, R.raw.kukushka);
        mPlayer2 = MediaPlayer.create(this, R.raw.pribojj);

        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2();
            }
        });

        mPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay1();
            }
        });
    }

    private void stopPlay1() {
        mPlayer1.stop();
        try {
            mPlayer1.prepare();
            mPlayer1.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlay2() {
        mPlayer2.stop();
        try {
            mPlayer1.prepare();
            mPlayer1.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play1(View view){
        mPlayer1.start();
    }

    public void play2(View view){
        mPlayer2.start();
    }
}