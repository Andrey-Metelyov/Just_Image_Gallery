package com.example.justimagegallery

import android.content.Context
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.justimagegallery.network.PicsumPhoto
import com.example.justimagegallery.overview.PhotoListAdapter

@GlideModule
class GlideApplication : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply {
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(
                ObjectKey(System.currentTimeMillis().toShort())
            )
        }
    }
}

@BindingAdapter("imageUrl")
fun bindPhoto(imgView: ImageView, downloadUrl: String?) {
    downloadUrl?.let {
        val downloadUri = downloadUrl.toUri().buildUpon().scheme("https").build()
        System.err.println(downloadUri)
        GlideApp.with(imgView.context)
            .load(downloadUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_loading_image)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PicsumPhoto>?) {
    val adapter = recyclerView.adapter as PhotoListAdapter
    adapter.submitList(data)
}
