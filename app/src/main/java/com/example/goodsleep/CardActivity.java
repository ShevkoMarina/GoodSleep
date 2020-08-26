package com.example.goodsleep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardActivity extends AppCompatActivity {

    ImageButton mPauseButton, mTimerButton, mAddButton;
    SeekBar mVolumeBar;
    int tHours, tMinute;
    TextView mTVTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        mPauseButton = findViewById(R.id.pauseButton);
        mTimerButton = findViewById(R.id.timerButton);
        mAddButton = findViewById(R.id.addSoundtrackButton);
        mVolumeBar = findViewById(R.id.volumeSeekBar);
        mTVTimer = findViewById(R.id.textView);


        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.kukushka);
        mPlayer.start();

        mTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CardActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHours = hourOfDay;
                                tMinute = minute;
                                String time = tHours + ":" + tMinute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    mTVTimer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(tHours, tMinute);
                timePickerDialog.show();
            }
        });
    }
}