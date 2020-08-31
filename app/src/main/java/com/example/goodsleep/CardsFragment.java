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
import java.util.Objects;

public class CardsFragment extends Fragment {

    private ArrayList<SoundItem> mRainItems;
    private ArrayList<SoundItem> mAllItems;
    private RecyclerView mMainRecyclerView;
    private CardsAdapter mCardsAdapter;

    public static CardsFragment newInstance() {
        Bundle args = new Bundle();
        CardsFragment fragment = new CardsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mCardsAdapter = new CardsAdapter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);
        initCardsRecyclerView(view);
        assert getArguments() != null;
        String category = getArguments().getString(MainActivity.CATEGORY_KEY);
        assert category != null;
        setCards(category);
        return view;
    }

    public void setCards(String key) {
        switch (key) {
            case "all":
                createAllSoundItems();
                setCardsAdapter(mAllItems, false);
                break;
            case "rain":
                createRainSoundItems();
                setCardsAdapter(mRainItems, true);
                break;
        }
    }

    private void setCardsAdapter(final ArrayList<SoundItem> list, boolean reset) {
        if (reset) {
            mCardsAdapter.clearItems();
        }

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
        mAllItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on window", new int[] {R.raw.rain_on_window}));
        mAllItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] {R.raw.thunderstorm}));
        mAllItems.add(new SoundItem(R.drawable.light_rain, "Light rain", new int[] {R.raw.light_rain}));
        mAllItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy rain", new int[] {R.raw.heavy_rain}));
        mAllItems.add(new SoundItem(R.drawable.steady_rain, "Steady rain", new int[] {R.raw.steady_rain}));
        mAllItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
        mAllItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on window", new int[] {R.raw.rain_on_window}));
        mAllItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] {R.raw.thunderstorm}));
        mAllItems.add(new SoundItem(R.drawable.light_rain, "Light rain", new int[] {R.raw.light_rain}));
        mAllItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy rain", new int[] {R.raw.heavy_rain}));
        mAllItems.add(new SoundItem(R.drawable.steady_rain, "Steady rain", new int[] {R.raw.steady_rain}));
        mAllItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
    }

    private void createRainSoundItems() {
        mRainItems = new ArrayList<>();
        mRainItems.add(new SoundItem(R.drawable.rain_on_window, "Rain on window", new int[] {R.raw.rain_on_window}));
        mRainItems.add(new SoundItem(R.drawable.thunder, "Thunderstorm", new int[] {R.raw.thunderstorm}));
        mRainItems.add(new SoundItem(R.drawable.light_rain, "Light rain", new int[] {R.raw.light_rain}));
        mRainItems.add(new SoundItem(R.drawable.heavy_rain, "Heavy rain", new int[] {R.raw.heavy_rain}));
        mRainItems.add(new SoundItem(R.drawable.steady_rain, "Steady rain", new int[] {R.raw.steady_rain}));
        mRainItems.add(new SoundItem(R.drawable.rain_on_umbrella, "Rain on umbrella", new int[] {R.raw.rain_falls_on_umbrella}));
    }
}