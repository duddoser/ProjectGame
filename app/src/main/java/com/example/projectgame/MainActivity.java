package com.example.projectgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationHost{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MusicPlayer musicPlayer = new MusicPlayer(this);
        setContentView(R.layout.activity_main);

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

        transaction.commit();
    }
}

