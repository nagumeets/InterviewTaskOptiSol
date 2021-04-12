package com.example.interviewtask.ui

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.interviewtask.R

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String,isImage:Boolean) {

    if (isImage) {
        Glide.with(this)
            .load(url).placeholder(R.drawable.placeholder)
            .apply(RequestOptions.fitCenterTransform().diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(this)
    }else{

        Glide.with(this)
            .load(R.drawable.placeholder)
            .apply(RequestOptions.fitCenterTransform().diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(this)
    }
}