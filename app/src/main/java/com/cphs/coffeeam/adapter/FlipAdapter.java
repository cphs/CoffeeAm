package com.cphs.coffeeam.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.cphs.coffeeam.R;
import com.cphs.coffeeam.model.Link;
import com.cphs.coffeeam.view.WebViewActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cesario.siringoringo on 20/12/2017.
 */

public class FlipAdapter extends BaseAdapter {
  private final String READ_MORE = " .Read More";
  private LayoutInflater inflater;
  private Callback callback;
  private List<Link> mLinks = new ArrayList<Link>();
  private Context mContext;

  public FlipAdapter(Context context, ArrayList<Link> links) {
    mContext = context;
    inflater = LayoutInflater.from(context);
    mLinks = links;
  }

  public void setCallback(Callback callback) {
    this.callback = callback;
  }

  @Override
  public int getCount() {
    return mLinks.size();
  }

  @Override
  public Object getItem(int position) {
    return position;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;

    if (convertView == null) {
      holder = new ViewHolder();
      convertView = inflater.inflate(R.layout.layout_link, parent, false);
      holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
      holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
      holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image);

      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    String desc = mLinks.get(position).getDescription();
    if (!TextUtils.isEmpty(desc)) {
      if (desc.length() > 150) {
        desc = desc.substring(0, 150);
      }
      desc += "...";
      desc += READ_MORE;

      SpannableString spannableString = new SpannableString(desc);
      int startPosition = desc.indexOf(READ_MORE);
      int endPosition = desc.lastIndexOf(READ_MORE) + READ_MORE.length();
      spannableString.setSpan(new ClickableSpan() {
        @Override
        public void onClick(View view) {
          openWebview(mLinks.get(position).getUrl().replace("\\", ""));
        }

        @Override
        public void updateDrawState(TextPaint ds) {
          ds.setColor(Color.BLUE);
          ds.setUnderlineText(false);
        }
      }, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

      holder.tvDesc.setText(spannableString);
      holder.tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
      holder.tvDesc.setVisibility(View.VISIBLE);
    } else {
      holder.tvDesc.setVisibility(View.GONE);
    }

    holder.tvTitle.setText(mLinks.get(position).getTitle());
    holder.tvTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openWebview(mLinks.get(position).getUrl().replace("\\", ""));
      }
    });
    if (!TextUtils.isEmpty(mLinks.get(position).getImageUrl())) {
      String imageUrl = mLinks.get(position).getImageUrl().replace("\\", "");
      Glide.with(mContext).load(imageUrl).centerCrop().into(holder.ivImage);
    }
    return convertView;
  }

  public void addItems(ArrayList<Link> links) {
    mLinks.addAll(links);
    notifyDataSetChanged();
  }

  private void openWebview(String url) {
    Intent intent = new Intent(mContext, WebViewActivity.class);
    intent.putExtra("url", url);
    mContext.startActivity(intent);
  }

  public interface Callback {
    public void onPageRequested(int page);
  }

  static class ViewHolder {
    TextView tvDesc;
    ImageView ivImage;
    TextView tvTitle;
  }

}
