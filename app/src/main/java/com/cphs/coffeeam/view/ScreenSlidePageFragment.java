/*
 * Copyright 2012 The Android Open Source Project Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable
 * law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations under the License.
 */

package com.cphs.coffeeam.view;

import com.cphs.coffeeam.R;
import com.cphs.coffeeam.adapter.FlipAdapter;
import com.cphs.coffeeam.databinding.FragmentScreenSlidePageBinding;
import com.cphs.coffeeam.model.Page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

public class ScreenSlidePageFragment extends Fragment
    implements FlipAdapter.Callback, FlipView.OnFlipListener, FlipView.OnOverFlipListener {

  public static final String ARG_PAGE = "page";

  private Page mPage;
  private FragmentScreenSlidePageBinding mBinding;
  private FlipAdapter mAdapter;

  public ScreenSlidePageFragment() {}

  public static ScreenSlidePageFragment create(Page page) {
    ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
    Bundle args = new Bundle();
    args.putParcelable(ARG_PAGE, page);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPage = getArguments().getParcelable(ARG_PAGE);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_screen_slide_page, container, false);

    mAdapter = new FlipAdapter(getContext(), mPage.getLinks());
    mAdapter.setCallback(this);
    mBinding.flipView.setAdapter(mAdapter);
    mBinding.flipView.setOnFlipListener(this);
    mBinding.flipView.peakNext(true);
    mBinding.flipView.setOverFlipMode(OverFlipMode.GLOW);
    mBinding.flipView.setOnOverFlipListener(this);
    return mBinding.getRoot();
  }

  @Override
  public void onPageRequested(int page) {
    mBinding.flipView.smoothFlipTo(page);
  }

  @Override
  public void onFlippedToPage(FlipView v, int position, long id) {
    // Log.i("pageflip", "Page: " + position);
    // if (position > mFlipView.getPageCount() - 3 && mFlipView.getPageCount() < 30) {
    // mAdapter.addItems(5);
    // }
  }

  @Override
  public void onOverFlip(FlipView v, OverFlipMode mode, boolean overFlippingPrevious,
      float overFlipDistance, float flipDistancePerPage) {
    Log.i("overflip", "overFlipDistance = " + overFlipDistance);
  }
}
