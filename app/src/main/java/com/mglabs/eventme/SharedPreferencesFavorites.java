package com.mglabs.eventme;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesFavorites implements Favorites {

    // SharedPrefernces instance
    private final SharedPreferences mPrefs;

    public SharedPreferencesFavorites(Context context) {
        mPrefs = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE);
    }

    @Override
    public boolean getFavorite(int id) {
        return mPrefs.getBoolean(String.valueOf(id), false);    //default value meaning if we
        // don't find the id we are looking for in the favs, we return false.
    }

    public void putFavorite(int id, boolean favorite) { // the boolean parameter indicates if we
        //want to save it as a favorite or not

        SharedPreferences.Editor editor = mPrefs.edit();

        if(favorite) {
            editor.putBoolean(String.valueOf(id), true);
        } else {
            editor.remove(String.valueOf(id));
        }
        editor.apply();
    }

    @Override
    public boolean toggleFavorite(int id) {
        boolean favorite = getFavorite(id);
        putFavorite(id, !favorite);
        return !favorite;
    }
}
