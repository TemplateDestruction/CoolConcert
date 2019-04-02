package com.breakout.myapplication.api;

import android.support.annotation.NonNull;

import com.breakout.myapplication.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {

//    private static

    private static OkHttpClient sClient;

    private static volatile EducationService sService;

    private ApiFactory() {
    }

    @NonNull
    public static EducationService getEducationService() {
        EducationService service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(EducationService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        sService = buildRetrofit().create(EducationService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
//                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

}
