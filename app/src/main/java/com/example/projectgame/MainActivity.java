package com.example.projectgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.projectgame.before_game.StartFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost, OnBackPressedListener,
        MediaPlayerInterface {
    private MediaPlayer mediaPlayer;
    private int MAX_VOLUME=50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.gamemusic);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        if (savedInstanceState == null){
            navigateTo(new StartFragment(), false);
        }
    }

    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    public void pauseMediaPlayer() {
        mediaPlayer.pause();
    }


}

