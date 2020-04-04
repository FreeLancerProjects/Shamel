package com.endpoint.shamel.share;


import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.endpoint.shamel.language.Language;
import com.endpoint.shamel.preferences.Preferences;


public class App extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Preferences.getInstance().getLanguage(newBase)));    }

}

