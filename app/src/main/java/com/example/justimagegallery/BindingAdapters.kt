package com.example.justimagegallery

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.justimagegallery.network.PicsumPhoto
import com.example.justimagegallery.overview.PhotoListAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindPhoto(imgView: ImageView, downloadUrl: String?) {
    downloadUrl?.let {
        val downloadUri = downloadUrl.toUri().buildUpon().scheme("https").build()
        System.err.println(downloadUri)
        Picasso.get()
            .load(downloadUri)
            .placeholder(R.drawable.ic_loading_image)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PicsumPhoto>?) {
    val adapter = recyclerView.adapter as PhotoListAdapter
    adapter.submitList(data)
}
