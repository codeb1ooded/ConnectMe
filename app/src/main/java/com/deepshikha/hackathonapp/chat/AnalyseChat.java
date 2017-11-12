package com.deepshikha.hackathonapp.chat;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

    String CLOUD_API_KEY = "AIzaSyDPoYCBP3ta17ZI27fRmxUKYgupukIuVlY";
    CloudNaturalLanguage naturalLanguageService;
    String username = "";
    AbstractDBAdapter db;
    Client api;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_chat);
        username = getIntent().getStringExtra("userto");
        db = new AbstractDBAdapter(AnalyseChat.this);
        api = new Client();
        textView = (TextView) findViewById(R.id.analyzed_text);

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

        final AnnotateTextRequest request = new AnnotateTextRequest();
        request.setDocument(document);
        request.setFeatures(features);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AnnotateTextResponse response =
                            naturalLanguageService.documents()
                                    .annotateText(request).execute();
                    final List<Entity> entityList = response.getEntities();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String entities = "";
                            for(Entity entity:entityList) {
                                entities += "\n" + entity.getName().toUpperCase();
                            }
                            textView.setText(entities);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // More code here
            }
        });

    }
}
