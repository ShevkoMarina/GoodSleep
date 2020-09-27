package com.example.goodsleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class CardsFragment extends Fragment {

    private ArrayList<SoundItem> mRainItems;
    private ArrayList<SoundItem> mSeaItems;
    private ArrayList<SoundItem> mNightItems;
    private ArrayList<SoundItem> mAllItems;
    private RecyclerView mMainRecyclerView;
    private CardsAdapter mCardsAdapter;
    private static int mPosition;

    private static final int ALL = 0;
    private static final int RAIN = 1;
    private static final int SEA = 2;
    private static final int NIGHT = 3;
    private static final String POSITION = "POSITION";

    public static CardsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        CardsFragment fragment = new CardsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCardsAdapter = new CardsAdapter();

        if (getArguments() != null) {
            mPosition = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);
        initCardsRecyclerView(view);

        createRainSoundItems();
        createSeaSoundItems();
        createNightSoundItems();
        createAllSoundItems();

        setCards(mPosition);
        return view;
    }

    public void setCards(int position) {
        switch (position) {
            case ALL:
                setCardsAdapter(mAllItems);
                break;
            case RAIN:
                setCardsAdapter(mRainItems);
                break;
            case SEA:
                setCardsAdapter(mSeaItems);
                break;
            case NIGHT:
                setCardsAdapter(mNightItems);
                break;
        }
    }

    private void setCardsAdapter(final ArrayList<SoundItem> list) {
        mCardsAdapter.setItems(list);
        mCardsAdapter.setOnItemClickListener(new CardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CardActivity.class);
                intent.putExtra("Sound Item", list.get(position));
                Objects.requireNonNull(getContext()).startActivity(intent);
            }
        });
        mMainRecyclerView.setAdapter(mCardsAdapter);
    }

    private void initCardsRecyclerView(View view) {
        mMainRecyclerView = view.findViewById(R.id.main_recycler_view);
        mMainRecyclerView.setHasFixedSize(true);
        mMainRecyclerView.setItemViewCacheSize(18);
        mMainRecyclerView.setDrawingCacheEnabled(true);
        mMainRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        mMainRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mMainRecyclerView.setAdapter(mCardsAdapter);
    }

    private void createAllSoundItems() {
        mAllItems = new ArrayList<>();
        int count = maxItemsSize();

        for (int i = 0; i < count; i++) {
            if (mRainItems.size() > i) {
                mAllItems.add(mRainItems.get(i));
            }
            if (mSeaItems.size() > i) {
                mAllItems.add(mSeaItems.get(i));
            }
            if (mNightItems.size() > i) {
                mAllItems.add(mNightItems.get(i));
            }
        }
    }

    private int maxItemsSize() {
        List<Integer> sizes = new ArrayList<>();
        sizes.add(mRainItems.size());
        sizes.add(mNightItems.size());
        sizes.add(mSeaItems.size());
        //sizes.add(mNatureItems.size());

        return Collections.max(sizes);
    }

    private void createRainSoundItems() {
        mRainItems = new ArrayList<>();
        mRainItems.add(new SoundItem(R.drawable.rain_on_window_low, R.drawable.rain_on_window, "Rain on Window", new int[] { R.raw.rain_on_window }));
        mRainItems.add(new SoundItem(R.drawable.thunderstorm_low, R.drawable.thunder, "Thunderstorm", new int[] { R.raw.thunderstorm }));
        mRainItems.add(new SoundItem(R.drawable.light_rain_low, R.drawable.light_rain, "Light Rain", new int[] { R.raw.light_rain }));
        mRainItems.add(new SoundItem(R.drawable.heavy_rain_low, R.drawable.heavy_rain, "Heavy Rain", new int[] { R.raw.heavy_rain }));
        mRainItems.add(new SoundItem(R.drawable.steady_rain_low, R.drawable.steady_rain, "Steady Rain", new int[] { R.raw.steady_rain }));
        mRainItems.add(new SoundItem(R.drawable.rain_on_umbrella_low, R.drawable.rain_on_umbrella, "Rain on Umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
    }

    private void createSeaSoundItems() {
        mSeaItems = new ArrayList<>();
        mSeaItems.add(new SoundItem(R.drawable.seascape_low, R.drawable.seascape, "Seascape", new int[] { R.raw.seascape }));
        mSeaItems.add(new SoundItem(R.drawable.sea_cave_low, R.drawable.sea_cave, "Sea Cave", new int[] { R.raw.sea_cave }));
        mSeaItems.add(new SoundItem(R.drawable.big_waves_low, R.drawable.waves, "Big Waves", new int[] { R.raw.big_waves }));
        mSeaItems.add(new SoundItem(R.drawable.sea_beach_low, R.drawable.sea_beach, "North Sea Island", new int[] { R.raw.north_sea_island }));
        mSeaItems.add(new SoundItem(R.drawable.ocean_low, R.drawable.ocean, "Ocean", new int[] { R.raw.ocean }));
        mSeaItems.add(new SoundItem(R.drawable.sea_wind_low, R.drawable.sea_wind, "Sea Wind", new int[] { R.raw.sea_wind }));
    }

    private void createNightSoundItems() {
        mNightItems = new ArrayList<>();
        mNightItems.add(new SoundItem(R.drawable.windy_night_low, R.drawable.windy_night, "Windy Night", new int[] { R.raw.windy_night }));
        mNightItems.add(new SoundItem(R.drawable.night_wildlife_low, R.drawable.night_wildlife, "Night Wildlife", new int[] { R.raw.night_wildlife }));
    }
}