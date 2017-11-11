package com.deepshikha.hackathonapp;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.deepshikha.hackathonapp.database.AbstractDBAdapter;
import com.deepshikha.hackathonapp.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    AbstractDBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new AbstractDBAdapter(MainActivity.this);
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, mainFragment, "MainFragment").commit();
        setTitle(getSharedPreferences("ConnectMe", MODE_PRIVATE).getString("username", "red"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle b = new Bundle();
                SearchFragment sf = new SearchFragment();
                b.putSerializable("keyword", query);
                sf.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, sf, "SearchFragment").commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_signOut) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle ("Confirm");
            builder.setMessage ("Are you sure you want to logout?");
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.dialog_confirm_layout, null);
            builder.setView(v);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getSharedPreferences("ConnectMe", MODE_PRIVATE).edit().putString("username", null).apply();
                    db.deleteLabelTable();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    ActivityCompat.finishAffinity(MainActivity.this);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("SearchFragment") != null &&
                getSupportFragmentManager().findFragmentByTag("SearchFragment").isVisible()) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, mainFragment, "MainFragment").commit();
        } else {
            super.onBackPressed();
        }
    }
}
