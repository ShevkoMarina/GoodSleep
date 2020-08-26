package com.example.goodsleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private
    MediaPlayer mPlayer1, mPlayer2;
    Button startButton1, startButton2, endButton1, endButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_slider);

        List<SliderItem> sliderItems = new ArrayList<>();

        // Here we can upload images on server and receive JSON but I am a lazy bitch
        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
        sliderItems.add(new SliderItem(R.drawable.image1, "Forest"));
        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));
        sliderItems.add(new SliderItem(R.drawable.image1, "Spring"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);



        /*
        startButton1 = findViewById(R.id.s1);
        startButton2 = findViewById(R.id.s2);
        endButton1 = findViewById(R.id.e1);
        endButton2 = findViewById(R.id.e2);


         */
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

    private void stopPlay1(){
        mPlayer1.stop();
        try {
            mPlayer1.prepare();
            mPlayer1.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlay2(){
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