package com.infix.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.infix.album.R
import com.infix.album.databinding.RowAlbumPhotoBinding
import com.infix.album.models.AlbumPhoto


class UserPhotoAdapter(private val photos: List<AlbumPhoto>) :
    RecyclerView.Adapter<UserPhotoAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(RowAlbumPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    inner class VH(private val view: RowAlbumPhotoBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(photo: AlbumPhoto) {
            view.apply {
                tvAlbumTitle.text = photo.title
                Glide.with(view.root.context).load(photo.thumbnailUrl)
                    .placeholder(R.color.grey)
                    .into(ivAlbumThumbnil)
            }
        }
    }

}