package com.example.goodsleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    private static CountDownTimer countDownTimer;
    public static long timeLeftInMilliseconds;
    private static boolean timerIsRunning;
    private static boolean timerFinishedWithNull = false;
    ImageButton mPauseButton, mAddButton;
    SeekBar mVolumeBar;
    int tHours, tMinute;
    static TextView tvTimer, tvSoundName;
    static MediaPlayer mPlayer;
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
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

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
                    countDownTimer.cancel();
                }
                else {
                    mPlayer.start();
                    mPauseButton.setImageResource(R.drawable.pause_icon);
                    isMusicPlaying = true;
                    countDownTimer.start();
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
                GoBack();
                break;
            }
        }
        return true;
    }


    public void GoBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
        mPlayer.stop();
    }

    private void InitTimePicker() {

        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                TimerFragment fragment = new TimerFragment();
                FrameLayout container = view.findViewById(R.id.timer_fragment_container);
                transaction.add(R.id.timer_fragment_container, fragment).commit();
                transaction.addToBackStack(null);
            }
        });
    }


    private void InitAddButton() {
        /*
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

         */
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, GifActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void StartTimer() {
        if (!timerIsRunning) {
            countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
                @Override
                public void onTick(long i) {
                    timeLeftInMilliseconds = i;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    tvTimer.setText("Timer");
                    timerIsRunning = false;
                    mPlayer.seekTo(0);
                    mPlayer.stop();
                }
            }.start();
        }
        timerIsRunning = true;
    }

    public static void StopTimer() {
        if (timerIsRunning) {
            countDownTimer.cancel();
            timerIsRunning = false;
        }
    }

    public static void updateTimer() {
        int hours = (int) timeLeftInMilliseconds / 3600000;
        int minute = (int) timeLeftInMilliseconds % 3600000 / 60000;
        int seconds = (int) timeLeftInMilliseconds %  60000 / 1000;
        String timeLeftText = "";

        if (hours > 0) {
            timeLeftText += hours + ":";
        }
        timeLeftText += "" + minute;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        tvTimer.setText(timeLeftText);
    }
}