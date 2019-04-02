package com.breakout.myapplication;

import android.app.Application;

import com.breakout.myapplication.api.ApiFactory;
import com.google.firebase.FirebaseApp;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class BestApp extends Application {

    private static BestApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        FirebaseApp.initializeApp(this);

        Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(24000))
                .downloader(new OkHttp3Downloader(getCacheDir(), 25000000))
                .build();
        Picasso.setSingletonInstance(picasso);

        ApiFactory.recreate();
//        RepositoryProvider.init();
    }

    public static BestApp getAppContext() {
        return sInstance;
    }

}




