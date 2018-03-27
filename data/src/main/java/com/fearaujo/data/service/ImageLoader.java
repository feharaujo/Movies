package com.fearaujo.data.service;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {

    private Picasso mPicasso;

    public void loadImage(Context context, ImageView imageView, String imgHash) {
        if (mPicasso == null) {
            Picasso.Builder builder = new Picasso.Builder(context);
            builder.loggingEnabled(true);
            mPicasso = builder.build();
        }

        mPicasso.load(UrlConstants.BASE_IMG_URL + imgHash)
                .into(imageView);

    }

}
