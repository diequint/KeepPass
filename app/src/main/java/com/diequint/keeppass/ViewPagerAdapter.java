package com.diequint.keeppass;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = new AllKeysFragment();
                break;
            case 1:
                fragment = new FavouritesFragment();
                break;
            case 2:
                    fragment = new RecientsFragment();
                break;
            case 3:
                fragment = new NotesFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() { return 4; }
}
