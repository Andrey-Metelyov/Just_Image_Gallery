package com.example.justimagegallery.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.justimagegallery.databinding.ListViewItemBinding
import com.example.justimagegallery.databinding.TitleViewItemBinding
import com.example.justimagegallery.network.PicsumPhoto

private const val ITEM_VIEW_TYPE_TITLE = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class PhotoListAdapter(val viewModel: OverviewViewModel) :
    ListAdapter<PicsumPhoto, RecyclerView.ViewHolder>(DiffCallback) {

    class PicsumPhotoViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PicsumPhoto) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    class PicsumPhotoTitleViewHolder(private var binding: TitleViewItemBinding, val viewModel: OverviewViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PicsumPhoto) {
            binding.photo = photo
            binding.button.setOnClickListener {
                System.err.println("setOnClickListener")
                viewModel.onButtonClick()
            }
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

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_VIEW_TYPE_TITLE
            else -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TITLE -> PicsumPhotoTitleViewHolder(TitleViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewModel)
            ITEM_VIEW_TYPE_ITEM -> PicsumPhotoViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo = getItem(position)
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_TITLE -> (holder as PicsumPhotoTitleViewHolder).bind(photo)
            ITEM_VIEW_TYPE_ITEM -> (holder as PicsumPhotoViewHolder).bind(photo)
        }
    }
}