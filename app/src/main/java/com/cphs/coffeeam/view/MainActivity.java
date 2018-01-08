package com.cphs.coffeeam.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.cphs.coffeeam.R;
import com.cphs.coffeeam.model.Channel;
import com.cphs.coffeeam.model.Link;
import com.cphs.coffeeam.network.Client;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
  private ArrayList<Channel> mChannels;
  private HashMap<Channel, ArrayList<Link>> mPages;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mChannels = new ArrayList<>();
    mPages = new HashMap<>();
    getChannels();
  }

  private void getLinks(final long start, final long end, final Channel channel) {
    Client.getService().getLinks(start, end, channel.getId()).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<Link>>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {
            // No implementation required
          }

          @Override
          public void onNext(@NonNull ArrayList<Link> links) {
            mPages.put(channel, links);
            if (mPages.size() == mChannels.size()) {
              Intent intent = new Intent(MainActivity.this, ScreenSlideActivity.class);
              intent.putExtra("channels", mPages);
              startActivity(intent);
              finish();
            }
          }

          @Override
          public void onError(@NonNull Throwable e) {
            new AlertDialog.Builder(MainActivity.this).setMessage(e.getMessage())
                .setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    getLinks(start, end, channel);
                    dialog.dismiss();
                  }
                }).show();
          }

          @Override
          public void onComplete() {
            // No implementation required
          }
        });
  }

  private void getChannels() {
    Client.getService().getChannels().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<Channel>>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {
            // No implementation required
          }

          @Override
          public void onNext(@NonNull ArrayList<Channel> channels) {
            mChannels.addAll(channels);
            getAllLink();
          }

          @Override
          public void onError(@NonNull Throwable e) {
            new AlertDialog.Builder(MainActivity.this).setMessage(e.getMessage())
                .setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    getChannels();
                    dialog.dismiss();
                  }
                }).show();
          }

          @Override
          public void onComplete() {
            // No implementation required
          }
        });
  }

  private void getAllLink() {
    for (Channel channel : mChannels) {
      getLinks(0, 9999999999999L, channel);
    }
  }
}
