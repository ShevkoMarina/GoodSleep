package com.example.goodsleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardActivity extends AppCompatActivity {

    ImageButton mPauseButton, mAddButton;
    SeekBar mVolumeBar;
    int tHours, tMinute;
    TextView tvTimer, tvSoundName;
    MediaPlayer mPlayer;
    AudioManager mAudioManager;
    ImageView mCardActivityBackground;
    int maxVolume;
    boolean isMusicPlaying;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        mPauseButton = findViewById(R.id.pauseButton);
        tvTimer = findViewById(R.id.timer_tv);
        mAddButton = findViewById(R.id. addSoundtrackButton);
        mVolumeBar = findViewById(R.id.volumeSeekBar);
        tvSoundName = findViewById(R.id.soundName);
        mCardActivityBackground = findViewById(R.id.card_activity_background);
        isMusicPlaying = true;

        InitTimePicker();
        InitVolumeBar();
        InitPause();
        InitAddButton();

        // Get info
        Intent intent = getIntent();
        SoundItem soundItem = intent.getParcelableExtra("Sound Item");
        tvSoundName.setText(soundItem.getName());
        mCardActivityBackground.setImageResource(soundItem.getImageSrc());
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
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN: {
                mVolumeBar.setProgress(mAudioManager.getStreamVolume(mAudioManager.STREAM_MUSIC) - maxVolume/15);
                break;
            }
            case KeyEvent.KEYCODE_VOLUME_UP: {
                mVolumeBar.setProgress(mAudioManager.getStreamVolume(mAudioManager.STREAM_MUSIC) + maxVolume/15);
                break;
            }
            case KeyEvent.KEYCODE_BACK: {
                super.onBackPressed();
                mPlayer.stop();
                break;
            }
        }
        return true;
    }

    private void stopPlay() {
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void InitTimePicker() {
        /*
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CardActivity.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
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
                                    tvTimer.setText(f12Hours.format(date));
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
         */

        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                TimerFragment fragment = new TimerFragment();
                FrameLayout container = view.findViewById(R.id.timer_fragment_container);
                transaction.add(R.id.timer_fragment_container, fragment).commit();
            }
        });
    }


    private void InitAddButton() {
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                AddSoundTracksFragment fragment = new AddSoundTracksFragment();
                // FrameLayout fragmentContainer = view.findViewById(R.id.sound_fragment_container);
                //fragmentContainer.setVisibility(View.VISIBLE);
                //  transaction.add(R.id.sound_fragment_container, fragment).commit();
            }
        });
    }
}