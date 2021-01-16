package com.infix.album.pages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.infix.album.R
import com.infix.album.adapter.UserPhotoAdapter
import com.infix.album.api.AppApi
import com.infix.album.databinding.ActivityAlbumPhotosBinding
import com.infix.album.models.AlbumPhoto
import com.infix.album.util.ALBUMID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumPhotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPhotos()
    }

    private fun getPhotos() {
        AppApi().getAlbumPictures(intent.getIntExtra(ALBUMID, 0))
            .enqueue(object : Callback<List<AlbumPhoto>> {
                override fun onResponse(
                    call: Call<List<AlbumPhoto>>,
                    response: Response<List<AlbumPhoto>>
                ) {
                    if (response.isSuccessful) setPhotos(response)
                    else showError(getString(R.string.try_again))
                }

                override fun onFailure(call: Call<List<AlbumPhoto>>, t: Throwable) {
                    showError(t.localizedMessage!!)
                }
            })
    }

    private fun setPhotos(response: Response<List<AlbumPhoto>>) {
        binding.apply {
            rvPhoto.adapter = UserPhotoAdapter(response.body()!!)
            pbPhoto.visibility = View.GONE
            rvPhoto.visibility = View.VISIBLE
        }
    }

    private fun showError(error: String) {
        binding.apply {
            tvError.text = error
            tvError.visibility = View.VISIBLE
            pbPhoto.visibility = View.GONE
        }
    }
}