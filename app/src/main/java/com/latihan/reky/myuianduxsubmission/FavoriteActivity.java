package com.latihan.reky.myuianduxsubmission;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.latihan.reky.myuianduxsubmission.favorite.ViewPagerAdapterFavorite;

import java.util.Objects;

public class FavoriteActivity extends AppCompatActivity {

    TabLayout tabLayoutFavorite;
    ViewPager viewPagerFavorite;
    ViewPagerAdapterFavorite vpAdapterFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        tabLayoutFavorite = findViewById(R.id. tab_view_favorite);
        viewPagerFavorite = findViewById(R.id.view_page_favorite);
        vpAdapterFavorite = new ViewPagerAdapterFavorite(this, getSupportFragmentManager());

        viewPagerFavorite.setAdapter(vpAdapterFavorite);
        tabLayoutFavorite.setupWithViewPager(viewPagerFavorite);

        Objects.requireNonNull(Objects.requireNonNull(tabLayoutFavorite.getTabAt(0)).setIcon(R.drawable.movies_favorite));
        Objects.requireNonNull(Objects.requireNonNull(tabLayoutFavorite.getTabAt(1)).setIcon(R.drawable.tv_show_favorite));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }
}
