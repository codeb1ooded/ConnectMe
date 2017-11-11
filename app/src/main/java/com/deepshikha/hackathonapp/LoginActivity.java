package com.deepshikha.hackathonapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = findViewById(R.id.username);
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
                if (username.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                preferences.edit().putString("username", username.getText().toString().trim())
                        .apply();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
