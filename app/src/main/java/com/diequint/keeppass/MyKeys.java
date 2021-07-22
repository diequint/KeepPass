package com.diequint.keeppass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

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
}