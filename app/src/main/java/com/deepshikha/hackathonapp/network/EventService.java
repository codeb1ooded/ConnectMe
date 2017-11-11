package com.deepshikha.hackathonapp.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by deepshikha.
 */

public interface EventService {
    @GET("createEvent")
    Call<Void> createEvent(@Query("name") String name, @Query("place") String place, @Query("date") String date);

    @GET("events/get")
    Call<List<String>> getEvents();

    @GET("register")
    Call<Void> register(@Query("username") String username, @Query("name") String eventName);
}
