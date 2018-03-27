package com.fearaujo.data.service;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitCreator {

    private static Retrofit mRetrofit;

    public static Retrofit getInstance(Context applicationContext) {
        if (mRetrofit == null) {
            File file = providesCacheFile(applicationContext);
            Cache cache = providesCache(file);
            OkHttpClient client = providesOkHttpClient(cache);

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(UrlConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(providesGsonConverterFactory());

            mRetrofit = retrofitBuilder.build();
        }

        return mRetrofit;
    }

    private static File providesCacheFile(Context context) {
        return new File(context.getCacheDir(), "http-cache");
    }

    private static Cache providesCache(File cacheFile) {
        // 30 MB of cache
        return new Cache(cacheFile, 30 * 1024 * 1024);
    }

    private static OkHttpClient providesOkHttpClient(Cache cache) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.writeTimeout(1, TimeUnit.MINUTES);
        okHttpClient.readTimeout(1, TimeUnit.MINUTES);
        okHttpClient.connectTimeout(1, TimeUnit.MINUTES);
        okHttpClient.cache(cache);
        return okHttpClient.build();
    }

    private static GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
