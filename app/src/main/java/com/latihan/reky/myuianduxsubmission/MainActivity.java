package com.latihan.reky.myuianduxsubmission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         TabLayout tabLayout;
         ViewPager viewPager;
         ViewPagerAdapter vpAdapter;

        tabLayout = findViewById(R.id.tab_view);
        viewPager = findViewById(R.id.view_page);
        vpAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.movie_icon));
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.tv_show_icon));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
            case R.id.favorite:
                Intent intent1 = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
