package com.latihan.reky.myuianduxsubmission;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
  private final Context mContext;

      ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int [] TAB_TITLES = new int[] {
            R.string.Movies,
            R.string.Tv_Show,
    };


    @NonNull
    @Override
    public Fragment getItem(int position) {
         Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new FragmentMovie();
                    break;
                case 1:
                    fragment = new FragmentTvShow();
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
