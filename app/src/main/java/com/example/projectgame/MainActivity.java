package com.example.projectgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.projectgame.before_game.StartFragment;

/* This is a simple android game, in which user can mine the resources and then build your
* little kingdom. The type of resource you get depends on the district, where you are.
* If the user don't have an opportunity to travel to another district, he can trade some resources
* to obtain the one he need.*/

/* Server part of the app runs on dudddoser.pythonanywhere.com/ */

/* This app is based on fragments, so there is one Activity and many Fragments.
* In MainActivity navigation between fragments and MediaPlayer customized methods are defined.*/
public class MainActivity extends AppCompatActivity implements NavigationHost, OnBackPressedListener,
        MediaPlayerInterface {
    private MediaPlayer mediaPlayer;

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

    @Override
    public void resumeMediaPlayer() {
        mediaPlayer.start();
    }


}

