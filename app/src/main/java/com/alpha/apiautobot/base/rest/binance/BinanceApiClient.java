package com.alpha.apiautobot.base.rest.binance;


import android.content.Context;
import com.alpha.apiautobot.base.rest.binance.BinanceApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Theo
 */
public final class BinanceApiClient {
    public static BinanceApiService service;
    private BinanceApiClient() {
    }
        public static String BASE_URL = "";
    public static Context context;

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static void CreateApiService(String url,OkHttpClient okHttpClient){
        BASE_URL = url;
        service = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(BinanceApiService.class);
    }

    public static OkHttpClient genericClient(Interceptor interceptor) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();
        return httpClient;
    }

}