package com.cphs.coffeeam.view;

import com.cphs.coffeeam.R;
import com.cphs.coffeeam.databinding.ActivityWebViewBinding;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
  private ActivityWebViewBinding mActivityWebViewBinding;
  private ProgressDialog progress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityWebViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
    progress = new ProgressDialog(this);
    progress.setMessage("Grab your coffee");
    progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progress.setIndeterminate(true);
    String url = getIntent().getStringExtra("url");

    mActivityWebViewBinding.webView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progress.show();
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progress.hide();
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }
    });
    mActivityWebViewBinding.webView.loadUrl(url);


  }
}
