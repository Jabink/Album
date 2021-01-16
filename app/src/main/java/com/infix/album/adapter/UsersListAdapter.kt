package com.infix.album.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.infix.album.databinding.RowUsersBinding
import com.infix.album.models.User
import com.infix.album.pages.UserAlbumActivity
import com.infix.album.util.USER
import com.infix.album.util.setRandomImage


class UsersListAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersListAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class VH(private val view: RowUsersBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(user: User) {
            view.apply {
                user.apply {
                    tvUserName.text = name
                    tvUserEmail.text = email
                    setRandomImage(name, ivUserProfile, 10)
                }
                this.root.setOnClickListener {
                    Log.d("AppLogs", "${user.id} ${user.name}  ${user.email} ")
                    val intent = Intent(view.root.context, UserAlbumActivity::class.java)
                    intent.putExtra(USER, Gson().toJson(user))
                    view.root.context.startActivity(intent)
                }
            }
        }
    }

}