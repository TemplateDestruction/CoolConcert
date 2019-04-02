package com.breakout.myapplication.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class ApiKeyInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic R2hZSjhDbU9mQ0NPamc2V0Ryc1FUQ1RWOG1CY0RWQ3Z1dDlRUHVXZjptejk4VnYyUXl4MjBJUzRQZENiYVRUQkRoYUZGS0RZbnFoSHc5ZWpqY3dtdnpDbG04SW5FYUJvdzJiU1FMUmdrcGZuaXRLOUtHOHBBUzhwbTNHVDBRTlVGSmlDZUNVbUE0TzdBTnp0elJBek9yZUlrZ1k1TzB6TG12NERVZHNlMw==")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build();
        return chain.proceed(request);
    }
}
