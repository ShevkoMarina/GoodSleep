package com.example.goodsleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class TimerFragment extends Fragment {

    private Button CustomSettingsButton, OffTimerButton, Button15Min, Button30Min, ButtonHour;
    private ImageButton CancelButton;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageButton cancelButton;

    public TimerFragment() {
        // Required empty public constructor
    }

    public static TimerFragment newInstance(String param1, String param2) {
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //    mParam1 = getArguments().getString(ARG_PARAM1);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.timer_fragment, container, false);
        CustomSettingsButton = view.findViewById(R.id.custom_timer_button);
        OffTimerButton = view.findViewById(R.id.off_timer_button);
        Button15Min = view.findViewById(R.id.min_15);
        Button30Min = view.findViewById(R.id.min_30);
        ButtonHour = view.findViewById(R.id.min_1);

        Button15Min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitTimer(9000);
            }
        });

        Button30Min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitTimer(1800000);
            }
        });

        ButtonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              InitTimer(3600000);
            }
        });

        OffTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardActivity.StopTimer();
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void InitTimer(long time) {
        CardActivity.StopTimer();
        CardActivity.timeLeftInMilliseconds = time;
        CardActivity.StartTimer();
        getActivity().onBackPressed();
    }
}