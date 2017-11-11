package com.deepshikha.hackathonapp.chat;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;
import com.deepshikha.hackathonapp.network.Client;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.language.v1beta2.CloudNaturalLanguage;
import com.google.api.services.language.v1beta2.CloudNaturalLanguageRequestInitializer;
import com.google.api.services.language.v1beta2.model.AnnotateTextRequest;
import com.google.api.services.language.v1beta2.model.AnnotateTextResponse;
import com.google.api.services.language.v1beta2.model.Document;
import com.google.api.services.language.v1beta2.model.Entity;
import com.google.api.services.language.v1beta2.model.Features;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalyseChat extends AppCompatActivity {

    String CLOUD_API_KEY = "AIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlYAIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlYAIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlYAIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlYAIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlYAIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlY";
    CloudNaturalLanguage naturalLanguageService;
    String username = "";
    AbstractDBAdapter db;
    Client api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_chat);
        username = getIntent().getStringExtra("userto");
        db = new AbstractDBAdapter(AnalyseChat.this);
        api = new Client();

        naturalLanguageService =
                new CloudNaturalLanguage.Builder(
                        AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(),
                        null
                ).setCloudNaturalLanguageRequestInitializer(
                        new CloudNaturalLanguageRequestInitializer(CLOUD_API_KEY)
                ).build();
        Document document = new Document();
        document.setType("PLAIN_TEXT");
        document.setLanguage("en-US");
        document.setContent(db.getText(username));
        Features features = new Features();
        features.setExtractEntities(true);
        features.setExtractDocumentSentiment(true);

        Call<AnalyseResponse> response = Client.getInterface().analyseCall(CLOUD_API_KEY, document, "UTF-8");
        response.enqueue(new Callback<AnalyseResponse>() {
            @Override
            public void onResponse(Call<AnalyseResponse> call, Response<AnalyseResponse> response) {
                String entities = "";
                for(Entity entity: response.body().entities) {
                    entities += "\n" + entity.getName().toUpperCase();
                }
                Log.i(getLocalClassName(), "This audio file talks about :"
                        + entities);
                AlertDialog dialog =
                        new AlertDialog.Builder(AnalyseChat.this)
                                .setMessage("This audio file talks about :"
                                        + entities)
                                .setNeutralButton("Okay", null)
                                .create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<AnalyseResponse> call, Throwable t) {
                Toast.makeText(AnalyseChat.this, "You are not connected to internet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
