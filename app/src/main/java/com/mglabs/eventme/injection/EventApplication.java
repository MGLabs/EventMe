package com.mglabs.eventme.injection;

import android.app.Application;

import com.mglabs.eventme.Favorites;
import com.mglabs.eventme.SharedPreferencesFavorites;

public class EventApplication extends Application {

    private Favorites favorites = null;

    public Favorites getFavorites() {
        if (favorites == null) {
            favorites = new SharedPreferencesFavorites(this);
        }
        return favorites;
    }
}
