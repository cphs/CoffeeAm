package com.cphs.coffeeam.adapter;

import java.util.ArrayList;

import com.cphs.coffeeam.model.Page;
import com.cphs.coffeeam.view.ScreenSlidePageFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class SlideAdapter extends FragmentStatePagerAdapter {
  private ArrayList<Page> mPages;

  public SlideAdapter(FragmentManager fm, ArrayList<Page> pages) {
    super(fm);
    mPages = pages;
  }

  @Override
  public Fragment getItem(int position) {
    return ScreenSlidePageFragment.create(mPages.get(position));
  }

  @Override
  public int getCount() {
    return mPages.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return "#" + mPages.get(position).getChannel().getName();
  }
}
