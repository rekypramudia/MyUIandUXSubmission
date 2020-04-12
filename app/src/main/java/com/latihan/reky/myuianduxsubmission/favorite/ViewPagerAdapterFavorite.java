package com.latihan.reky.myuianduxsubmission.favorite;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.latihan.reky.myuianduxsubmission.R;

public class ViewPagerAdapterFavorite extends FragmentPagerAdapter {

    private final Context mContext;

     public ViewPagerAdapterFavorite(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int [] TAB_TITLES = new int[] {
            R.string.movie_favorite,
            R.string.show_favorite,
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentMovieFavorite();
                break;
            case 1:
                fragment = new FragmentTvFavorite();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
