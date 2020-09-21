package com.unito.toshop.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.unito.toshop.view.fragment.ViewFHProduct;
import com.unito.toshop.view.fragment.ViewSHProduct;

public class HomeTabAdapter extends FragmentStatePagerAdapter {

    private int numTabs;
    private ViewFHProduct viewFHProduct = new ViewFHProduct();
    private ViewSHProduct viewSHProduct = new ViewSHProduct();

    public HomeTabAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return viewFHProduct;
            case 1:
                return viewSHProduct;
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.numTabs;
    }

}
