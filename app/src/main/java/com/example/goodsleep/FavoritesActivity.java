package com.example.goodsleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView mFavCards;
    private BottomNavigationView mBottomNavigationView;
    private SharedPreferences mSharedPreferences;
    private CardsAdapter mCardsAdapter;
    private ArrayList<SoundItem> mFavItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        mFavCards = findViewById(R.id.fav_cards_rv);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.fav);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        finish();
                        return true;
                    case R.id.fav:
                        return true;
                }

                return false;
            }
        });

        mCardsAdapter = new CardsAdapter();
        mSharedPreferences = getSharedPreferences("FAV", MODE_PRIVATE);
        initCardsRecyclerView();
        mFavItems = getFavItems();
        mCardsAdapter.setItems(mFavItems);
        getSupportActionBar().setTitle("Favorites");
    }

    private boolean isFav(SoundItem soundItem) {
        return mSharedPreferences.contains(soundItem.getName());
    }

    private void initCardsRecyclerView() {
        mFavCards = findViewById(R.id.fav_cards_rv);
        mFavCards.setLayoutManager(new GridLayoutManager(this, 2));
        mCardsAdapter.setOnItemClickListener(new CardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FavoritesActivity.this, CardActivity.class);
                intent.putExtra("Sound Item", mFavItems.get(position));
                startActivity(intent);
            }
        });
        mFavCards.setAdapter(mCardsAdapter);
    }

    private ArrayList<SoundItem> getFavItems() {
        ArrayList<SoundItem> items = new ArrayList<>();

        for (SoundItem soundItem : CardsFragment.mAllItems) {
            if (isFav(soundItem)) {
                items.add(soundItem);
            }
        }

        return items;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomNavigationView.setSelectedItemId(R.id.fav);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}