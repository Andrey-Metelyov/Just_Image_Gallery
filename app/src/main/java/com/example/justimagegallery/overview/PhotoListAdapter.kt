package com.example.justimagegallery.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.justimagegallery.databinding.ListViewItemBinding
import com.example.justimagegallery.network.PicsumPhoto

class PhotoListAdapter() :
    ListAdapter<PicsumPhoto, PhotoListAdapter.PicsumPhotoViewHolder>(DiffCallback) {

    class PicsumPhotoViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PicsumPhoto) {
            binding.photo = photo
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<PicsumPhoto>() {
        override fun areItemsTheSame(oldItem: PicsumPhoto, newItem: PicsumPhoto): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PicsumPhoto, newItem: PicsumPhoto): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumPhotoViewHolder {
        return PicsumPhotoViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PicsumPhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}