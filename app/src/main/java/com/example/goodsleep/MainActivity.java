package com.example.goodsleep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton mCardItem;
    private MediaPlayer mPlayer1, mPlayer2;
    private List<SoundItem> SoundItems;
    private List<CategoryItem> CategoryItems;
    private LinearLayout mLinearLayout;
    private RecyclerView mHorizontalRecyclerView, mVerticalRecyclerView;
    private SliderAdapter mAdapter;
    private CategoryAdapter categoryAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
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

         */

        createCategoryItems();
        IniVerticalRecyclerView();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createCategoryItems() {
        CategoryItems = new ArrayList<>();
        CategoryItems.add(new CategoryItem("Rain", createRainSoundItems()));
        CategoryItems.add(new CategoryItem("Nature", createRainSoundItems()));
        CategoryItems.add(new CategoryItem("Sea", createRainSoundItems()));
        CategoryItems.add(new CategoryItem("Night", createRainSoundItems()));
    }

    private List<SoundItem> createRainSoundItems() {
        List<SoundItem> RainItems = new ArrayList<>();
        RainItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on window", new int[] {R.raw.rain_on_window}));
        RainItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] {R.raw.thunderstorm}));
        RainItems.add(new SoundItem(R.drawable.light_rain, "Light rain", new int[] {R.raw.light_rain}));
        RainItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy rain", new int[] {R.raw.heavy_rain}));
        RainItems.add(new SoundItem(R.drawable.steady_rain, "Steady rain", new int[] {R.raw.steady_rain}));
        RainItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
        return RainItems;
    }

    private void IniVerticalRecyclerView() {
        mVerticalRecyclerView = findViewById(R.id.main_vertical_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mVerticalRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, CategoryItems);
        mVerticalRecyclerView.setAdapter(categoryAdapter);
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
