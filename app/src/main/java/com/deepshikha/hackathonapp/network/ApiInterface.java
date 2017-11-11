package com.deepshikha.hackathonapp.network;

import android.util.Xml;

import com.deepshikha.hackathonapp.chat.AnalyseResponse;
import com.google.api.services.language.v1beta2.model.Document;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by megha on 12/11/17.
 */

public interface ApiInterface {
    @POST(".")
    Call<AnalyseResponse> analyseCall(String key, Document document, String encodingType);
}
