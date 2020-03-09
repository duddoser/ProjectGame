package com.example.projectgame;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;

    public MusicPlayer(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.gamemusic);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public MusicPlayer(Fragment fragment){
        Context context = fragment.getContext();
        mediaPlayer = MediaPlayer.create(context, R.raw.gamemusic);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopPlaying(){
        mediaPlayer.stop();
    }

}
