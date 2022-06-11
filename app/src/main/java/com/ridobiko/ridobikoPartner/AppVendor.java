package com.ridobiko.ridobikoPartner;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ridobiko.ridobikoPartner.api.ApiService;
import com.ridobiko.ridobikoPartner.constants.Constants;
import com.ridobiko.ridobikoPartner.models.BookingResponseModel;
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppVendor extends Application {
    private static ApiService service;
    public static BookingResponseModel selectedBooking;
    public static MyBikesResponseModel selectedMyBike;
    public static String fuel_price;
    public static Boolean uploaded=true;
    public static ApiService buildApiService() {

        if (service == null) {
            OkHttpClient.Builder builder;
            builder = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request.Builder ongoing = chain.request().newBuilder();
                        ongoing.addHeader("Accept", "application/json");
                        Response response = chain.proceed(ongoing.build());
                        return response;
                    });
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build();
            service = retrofit.create(ApiService.class);
        }
        return service;
    }
}
