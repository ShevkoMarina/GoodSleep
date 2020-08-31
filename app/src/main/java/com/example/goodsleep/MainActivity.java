package com.example.goodsleep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String CATEGORY_KEY = "CATEGORY_KEY";
    private MediaPlayer mPlayer1, mPlayer2;
    private List<CategoryButton> mCategoryButtons;
    private LinearLayout mLinearLayout;
    private RecyclerView mButtonsRecyclerView;
    private ButtonsAdapter mButtonsAdapter;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

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

        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_KEY, "all");

        CardsFragment fragment = new CardsFragment();
        fragment.setArguments(bundle);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.cards_fragment_container, fragment)
                    .addToBackStack(CardsFragment.class.getName())
                    .commit();
        }

        mButtonsAdapter = new ButtonsAdapter(fragment);

        initButtonsRecyclerView();
        setCategoryButtons();
    }

    private void setCategoryButtons() {
        createCategoryButtons();
        mButtonsAdapter.setItems(mCategoryButtons);
    }

    private void createCategoryButtons() {
        mCategoryButtons = new ArrayList<>();
        mCategoryButtons.add(new CategoryButton("all"));
        mCategoryButtons.add(new CategoryButton("rain"));
        mCategoryButtons.add(new CategoryButton("nature"));
        mCategoryButtons.add(new CategoryButton("sea"));
        mCategoryButtons.add(new CategoryButton("night"));
    }

    private void initButtonsRecyclerView() {
        mButtonsRecyclerView = findViewById(R.id.buttons_recycler_view);
        mButtonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mButtonsRecyclerView.setAdapter(mButtonsAdapter);
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
        //TODO

        switch (item.getItemId()) {
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
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
