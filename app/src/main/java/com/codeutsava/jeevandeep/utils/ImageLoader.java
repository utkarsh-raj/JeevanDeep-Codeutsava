package com.codeutsava.jeevandeep.utils;

import android.widget.ImageView;
import android.widget.ProgressBar;

public interface ImageLoader {

    void loadImage(String url, ImageView imageView, ProgressBar progressBar);

    void loadImage1(String image_url, ImageView aah_imageView);

    void load_circular_image(String url, ImageView imageView);
}
