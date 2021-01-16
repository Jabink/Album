package com.infix.album.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.infix.album.R
import com.infix.album.adapter.UsersAlbumAdapter
import com.infix.album.api.AppApi
import com.infix.album.databinding.ActivityUserAlbumBinding
import com.infix.album.models.User
import com.infix.album.models.UserAlbum
import com.infix.album.util.USER
import com.infix.album.util.setRandomImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAlbumBinding
    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        intent.apply {
            user = Gson().fromJson(getStringExtra(USER)!!, User::class.java)
            binding.apply {
                setRandomImage(user.name, ivUserProfile, 10)
                tvUserName.text = user.name
                tvUserEmail.text = user.email
                btnCall.setOnClickListener {
                    openCall(user.phone)
                }
                btnLocation.setOnClickListener {
                    openMap(
                        user.address.geo.lat,
                        user.address.geo.lng
                    )
                }
            }
        }
        getUserAlbums()
    }

    private fun openMap(lat: String, lng: String) {
        val uri = "http://maps.google.com/maps?q=loc:$lat,$lng"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun openCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun getUserAlbums() {
        AppApi().getUserAlbums(user.id).enqueue(object : Callback<List<UserAlbum>> {
            override fun onResponse(
                call: Call<List<UserAlbum>>,
                response: Response<List<UserAlbum>>
            ) {
                if (response.isSuccessful) setUsersAlbums(response)
                else showError(getString(R.string.try_again))
            }

            override fun onFailure(call: Call<List<UserAlbum>>, t: Throwable) {
                showError(t.localizedMessage!!)
            }
        })
    }

    private fun setUsersAlbums(response: Response<List<UserAlbum>>) {
        binding.apply {
            rvAlbum.adapter = UsersAlbumAdapter(response.body()!!)
            pbAlbum.visibility = View.GONE
            rvAlbum.visibility = View.VISIBLE
        }
    }

    private fun showError(error: String) {
        binding.apply {
            tvError.text = error
            tvError.visibility = View.VISIBLE
            pbAlbum.visibility = View.GONE
        }
    }

}