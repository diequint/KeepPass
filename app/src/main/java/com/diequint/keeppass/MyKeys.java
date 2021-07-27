package com.diequint.keeppass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        tabLayout = findViewById(R.id.tabLayout);
        //Below must be replaced with whatever comes from SharedPreferences
        Boolean showTab = true;
        if (showTab) {
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
}