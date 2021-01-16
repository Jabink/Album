package com.infix.album.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infix.album.databinding.RowUserAlbumBinding
import com.infix.album.models.UserAlbum
import com.infix.album.pages.AlbumPhotosActivity
import com.infix.album.util.ALBUMID
import com.infix.album.util.setRandomImage


class UsersAlbumAdapter(private val albums: List<UserAlbum>) :
    RecyclerView.Adapter<UsersAlbumAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(RowUserAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount() = albums.size

    inner class VH(private val view: RowUserAlbumBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(album: UserAlbum) {
            view.apply {
                this.root.setOnClickListener {
                    Intent(
                        view.root.context,
                        AlbumPhotosActivity::class.java
                    ).apply {
                        putExtra(ALBUMID, album.albumId)
                        view.root.context.startActivity(this)
                    }

                }
                tvAlbumTitle.text = album.title
                setRandomImage(album.title,ivAlbumThumbnil,0)
            }
        }
    }

}