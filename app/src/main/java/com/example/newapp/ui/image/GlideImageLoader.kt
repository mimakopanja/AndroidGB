package com.example.newapp.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newapp.mvp.model.image.IImageLoader

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .circleCrop()
            .into(container)
    }

}