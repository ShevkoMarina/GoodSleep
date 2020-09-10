package com.example.goodsleep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager2 mViewPager;
    private FragmentStateAdapter mFragmentStateAdapter;
    private static List<CategoryButton> mCategoryButtons;
    private RecyclerView mButtonsRecyclerView;
    private static ButtonsAdapter mButtonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_ActionBar_Transparent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

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
        mCategoryButtons.add(new CategoryButton("night"));
        mCategoryButtons.add(new CategoryButton("nature"));
    }

    private void initButtonsRecyclerView() {
        mButtonsRecyclerView = findViewById(R.id.buttons_recycler_view);
        mButtonsRecyclerView.setHasFixedSize(true);
        mButtonsRecyclerView.setItemViewCacheSize(5);
        mButtonsRecyclerView.setDrawingCacheEnabled(true);
        mButtonsRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId()) {
//            default:
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }
}
