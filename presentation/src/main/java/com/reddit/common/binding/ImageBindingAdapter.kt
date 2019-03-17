package com.reddit.common.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.reddit.di.glide.GlideApp


object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = [
        "bind:img_uri",
        "bind:img_placeholder",
        "bind:img_fallback"
    ], requireAll = false)
    fun loadImage(imageView: ImageView, uri: String?, placeHolder: Drawable?, fallback: Drawable?) {
        GlideApp
                .with(imageView.context)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .dontAnimate()
                .placeholder(placeHolder)
                .fallback(fallback)
                .into(imageView)


    }

}