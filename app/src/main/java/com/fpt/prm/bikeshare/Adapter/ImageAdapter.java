package com.fpt.prm.bikeshare.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
//    private int[] mImageIds = new int[]{R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_text_dark };
    private List<String> imageResource;
    public ImageAdapter(Context context, List listImageUrl){
        mContext = context;
        imageResource = listImageUrl;
    }

    @Override
    public int getCount() {
        return imageResource.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(mImageIds[position]);
//        new ProcessImage.GetImageFromURL(imageView).execute(imageResource.get(position));
        Picasso.get().load(imageResource.get(position)).into(imageView);
        container.addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
