package com.example.goodsleep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager2 mViewPager;
    private FragmentStateAdapter mFragmentStateAdapter;
    private MediaPlayer mPlayer1, mPlayer2;
    private static List<CategoryButton> mCategoryButtons;
    private RecyclerView mButtonsRecyclerView;
    private static ButtonsAdapter mButtonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

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
        mViewPager = findViewById(R.id.main_view_pager);
        mFragmentStateAdapter = new ScreenSlidePagerAdapter(this);
        mViewPager.setAdapter(mFragmentStateAdapter);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mButtonsAdapter.disableButton(position);
            }
        });

        mButtonsAdapter = new ButtonsAdapter(mViewPager);

        initButtonsRecyclerView();
        setCategoryButtons();
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return CardsFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    private void setCategoryButtons() {
        createCategoryButtons();
        mButtonsAdapter.setItems(mCategoryButtons);
    }

    private void createCategoryButtons() {
        mCategoryButtons = new ArrayList<>();
        mCategoryButtons.add(new CategoryButton("all"));
        mCategoryButtons.add(new CategoryButton("rain"));
        mCategoryButtons.add(new CategoryButton("sea"));
        mCategoryButtons.add(new CategoryButton("nature"));
        mCategoryButtons.add(new CategoryButton("night"));
    }

    private void initButtonsRecyclerView() {
        mButtonsRecyclerView = findViewById(R.id.buttons_recycler_view);
        mButtonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mButtonsRecyclerView.setAdapter(mButtonsAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId()) {
//            default:
//                break;
//        }

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
