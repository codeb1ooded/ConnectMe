package com.deepshikha.hackathonapp.network;

import android.util.Log;

import com.deepshikha.hackathonapp.Callbacks;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deepshikha .
 */

public class NetworkManager {

    private static final NetworkManager ourInstance = new NetworkManager();
    private Retrofit retrofit;
    private EventService eventService;
    private RecommendationService recommendationService;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.16.20.110:3000/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        eventService = retrofit.create(EventService.class);
        recommendationService = retrofit.create(RecommendationService.class);
    }

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    public boolean createEvent(String name, String place, String date) {
        try {
            eventService.createEvent(name, place, date).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getEvents(final Callbacks.ListEventCallback callback) {
        Log.d("tag", "making call to: " + eventService.getEvents().request().toString());

        eventService.getEvents().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                callback.listEvents(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                callback.listEvents(new ArrayList<String>());
            }
        });
    }

    public void register(String username, String eventName, final Callbacks.RegistrationCallback registrationCallback) {
        eventService.register(username, eventName).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                registrationCallback.registrationStatus(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registrationCallback.registrationStatus(false);
            }
        });

    }

    public void getRecommendations(String username, final Callbacks.RecommendationCallback callback) {
        recommendationService.getRecommendations(username).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                callback.recommendations(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                callback.recommendations(new ArrayList<String>());
            }
        });
    }

}
