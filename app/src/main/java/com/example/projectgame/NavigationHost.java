package com.example.projectgame;

import androidx.fragment.app.Fragment;

//this interface simplifies navigation between fragments
public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
