package com.example.goodsleep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton mCardItem;
    private MediaPlayer mPlayer1, mPlayer2;
    private List<SoundItem> SoundItems;
    private LinearLayout mLinearLayout;
    private RecyclerView mHorizontalRecyclerView;
    private SliderAdapter mAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer1 = MediaPlayer.create(this, R.raw.kukushka);
        mPlayer2 = MediaPlayer.create(this, R.raw.pribojj);

        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayer2.start();
            }
        });

        mPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay1();
            }
        });

        createSoundItems();
        InitHorizontalRecycleView();
    }

    private void createSoundItems() {
        SoundItems = new ArrayList<>();
        SoundItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on window", new int[] {R.raw.rain_on_window}));
        SoundItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] {R.raw.thunderstorm}));
        SoundItems.add(new SoundItem(R.drawable.light_rain, "Light rain", new int[] {R.raw.light_rain}));
        SoundItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy rain", new int[] {R.raw.heavy_rain}));
        SoundItems.add(new SoundItem(R.drawable.steady_rain, "Steady rain", new int[] {R.raw.steady_rain}));
        SoundItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
    }
    
    private void InitHorizontalRecycleView() {
        mHorizontalRecyclerView = findViewById(R.id.main_h_recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new SliderAdapter(SoundItems);

        mHorizontalRecyclerView.setLayoutManager(mLinearLayoutManager);
        mHorizontalRecyclerView.setAdapter(mAdapter);
        mHorizontalRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                intent.putExtra("Sound Item", SoundItems.get(position));
                startActivity(intent);
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
}
