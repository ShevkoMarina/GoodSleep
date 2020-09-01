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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardsFragment extends Fragment {

    private ArrayList<SoundItem> mRainItems;
    private ArrayList<SoundItem> mSeaItems;
    private ArrayList<SoundItem> mAllItems;
    private RecyclerView mMainRecyclerView;
    private CardsAdapter mCardsAdapter;
    private static int mPosition;

    private static final int ALL = 0;
    private static final int RAIN = 1;
    private static final int SEA = 2;
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

        setCards(mPosition);
        return view;
    }

    public void setCards(int position) {
        switch (position) {
            case ALL:
                createAllSoundItems();
                setCardsAdapter(mAllItems, false);
                break;
            case RAIN:
                createRainSoundItems();
                setCardsAdapter(mRainItems, true);
                break;
            case SEA:
                createSeaSoundItems();
                setCardsAdapter(mSeaItems, true);
                break;
        }
    }

    private void setCardsAdapter(final ArrayList<SoundItem> list, boolean reset) {
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
        mMainRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mMainRecyclerView.setAdapter(mCardsAdapter);
    }

    private void createAllSoundItems() {
        mAllItems = new ArrayList<>();
        mAllItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on Window", new int[] { R.raw.rain_on_window }));
        mAllItems.add(new SoundItem(R.drawable.seascape, "Seascape", new int[] { R.raw.seascape }));
        mAllItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] { R.raw.thunderstorm }));
        mAllItems.add(new SoundItem(R.drawable.sea_cave, "Sea Cave", new int[] { R.raw.sea_cave }));
        mAllItems.add(new SoundItem(R.drawable.light_rain, "Light Rain", new int[] { R.raw.light_rain }));
        mAllItems.add(new SoundItem(R.drawable.waves, "Big Waves", new int[] { R.raw.big_waves }));
        mAllItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy Rain", new int[] { R.raw.heavy_rain }));
        mAllItems.add(new SoundItem(R.drawable.sea_beach, "North Sea Island", new int[] { R.raw.north_sea_island }));
        mAllItems.add(new SoundItem(R.drawable.steady_rain, "Steady Rain", new int[] { R.raw.steady_rain }));
        mAllItems.add(new SoundItem(R.drawable.ocean, "Ocean", new int[] { R.raw.ocean }));
        mAllItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on Umbrella", new int[] { R.raw.rain_falls_on_umbrella }));
        mAllItems.add(new SoundItem(R.drawable.sea_wind, "Sea Wind", new int[] { R.raw.sea_wind }));
    }

    private void createRainSoundItems() {
        mRainItems = new ArrayList<>();
        mRainItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on Window", new int[] { R.raw.rain_on_window }));
        mRainItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] { R.raw.thunderstorm }));
        mRainItems.add(new SoundItem(R.drawable.light_rain, "Light Rain", new int[] { R.raw.light_rain }));
        mRainItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy Rain", new int[] { R.raw.heavy_rain }));
        mRainItems.add(new SoundItem(R.drawable.steady_rain, "Steady Rain", new int[] { R.raw.steady_rain }));
        mRainItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on Umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
    }

    private void createSeaSoundItems() {
        mSeaItems = new ArrayList<>();
        mSeaItems.add(new SoundItem(R.drawable.seascape, "Seascape", new int[] { R.raw.seascape }));
        mSeaItems.add(new SoundItem(R.drawable.sea_cave, "Sea Cave", new int[] { R.raw.sea_cave }));
        mSeaItems.add(new SoundItem(R.drawable.waves, "Big Waves", new int[] { R.raw.big_waves }));
        mSeaItems.add(new SoundItem(R.drawable.sea_beach, "North Sea Island", new int[] { R.raw.north_sea_island }));
        mSeaItems.add(new SoundItem(R.drawable.ocean, "Ocean", new int[] { R.raw.ocean }));
        mSeaItems.add(new SoundItem(R.drawable.sea_wind, "Sea Wind", new int[] { R.raw.sea_wind }));
    }
}