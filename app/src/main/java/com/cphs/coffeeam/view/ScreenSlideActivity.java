package com.cphs.coffeeam.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.cphs.coffeeam.R;
import com.cphs.coffeeam.adapter.SlideAdapter;
import com.cphs.coffeeam.databinding.ActivityScreenSlideBinding;
import com.cphs.coffeeam.model.Channel;
import com.cphs.coffeeam.model.Link;
import com.cphs.coffeeam.model.Page;
import com.cphs.coffeeam.model.PageComparator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;

public class ScreenSlideActivity extends FragmentActivity {

  private PagerAdapter mPagerAdapter;
  private ActivityScreenSlideBinding mBinding;
  private HashMap<Channel, ArrayList<Link>> mMap;
  private ArrayList<Page> mPages;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_screen_slide);
    mPages = new ArrayList<>();
    mMap = (HashMap<Channel, ArrayList<Link>>) getIntent().getSerializableExtra("channels");
    for (Map.Entry<Channel, ArrayList<Link>> entry : mMap.entrySet()) {
      if (entry.getValue().size() > 0) {
        mPages.add(new Page(entry.getKey(), entry.getValue()));
      }
    }
    Collections.sort(mPages, new PageComparator());
    mPagerAdapter = new SlideAdapter(getSupportFragmentManager(), mPages);
    mBinding.viewPager.setAdapter(mPagerAdapter);
    mBinding.tabs.setupWithViewPager(mBinding.viewPager);
  }
}
