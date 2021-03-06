package com.diequint.keeppass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MyKeys extends AppCompatActivity {
    ViewPagerAdapter vpAdapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_keys);
        getSupportActionBar().setTitle(R.string.myCredentials);

        viewPager2 = findViewById(R.id.viewPager2);
        vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(vpAdapter);
        viewPager2.setCurrentItem(1, false);
        tabLayout = findViewById(R.id.tabLayout);
        loadPreferences();

        FloatingActionButton syncThis = findViewById(R.id.syncThis);
        FloatingActionButton newEntry = findViewById(R.id.newEntry);
        syncThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Sync now",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SyncData.class);
                startActivity(intent);
            }
        });
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"New Entry",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Credential.class);
                intent.putExtra("title", getString(R.string.addNew));
                //intent.putExtra("id", 0);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchButton:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsButton:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int appearance = Integer.parseInt(preferences.getString("visualise", "1"));
        if (appearance == 2 || appearance ==4) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if (appearance <= 2) {
            int[] icon = new int[]{
                    R.drawable.ic_all,
                    R.drawable.ic_favourites,
                    R.drawable.ic_recients,
                    R.drawable.ic_notes
            };
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setIcon(icon[position])).attach();
        } else {
            int[] text = new int[]{
                    R.string.allKeysTab,
                    R.string.favTab,
                    R.string.recientTab,
                    R.string.notesTab
            };
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(text[position])).attach();
        }
    }
}