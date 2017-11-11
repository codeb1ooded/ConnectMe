package com.deepshikha.hackathonapp.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by deepshikha .
 */

public interface RecommendationService {
    @GET("attendee")
    Call<List<String>> getRecommendations(@Query("username") String username);
}
