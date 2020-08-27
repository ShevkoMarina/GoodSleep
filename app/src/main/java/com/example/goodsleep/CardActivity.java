package com.example.goodsleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    TextView mTVTimer, tvSoundName;
    MediaPlayer mPlayer;
    AudioManager mAudioManager;
    ConstraintLayout mMainContainer;
    int maxVolume;
    boolean isMusicPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        mPauseButton = findViewById(R.id.pauseButton);
        mTimerButton = findViewById(R.id.timerButton);
        mAddButton = findViewById(R.id.addSoundtrackButton);
        mVolumeBar = findViewById(R.id.volumeSeekBar);
        tvSoundName = findViewById(R.id.soundName);
        mMainContainer = findViewById(R.id.card_activity_container);
        isMusicPlaying = true;



        InitTimePicker();
        InitVolumeBar();
        InitPause();

        // Get info
        Intent intent = getIntent();
        SoundItem soundItem = intent.getParcelableExtra("Sound Item");
        tvSoundName.setText(soundItem.getName());
        mMainContainer.setBackgroundResource(soundItem.getImageSrc());
        mPlayer = MediaPlayer.create(this, soundItem.getSoundTracks()[0]);


        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mVolumeBar.setMax(maxVolume);
        mVolumeBar.setProgress(currentVolume);

        mPlayer.start();

    }

    private void InitPause() {
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMusicPlaying) {
                    mPlayer.pause();
                    mPauseButton.setImageResource(R.drawable.play_icon);
                    isMusicPlaying = false;
                }
                else {
                    mPlayer.start();
                    mPauseButton.setImageResource(R.drawable.pause_icon);
                    isMusicPlaying = true;
                }
            }
        });
    }


    private void InitVolumeBar() {
        mVolumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(mAudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            mVolumeBar.setProgress(mAudioManager.getStreamVolume(mAudioManager.STREAM_MUSIC) - maxVolume/15);
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            mVolumeBar.setProgress(mAudioManager.getStreamVolume(mAudioManager.STREAM_MUSIC) + maxVolume/15);
        }
        return true;
    }


    private void InitTimePicker() {
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