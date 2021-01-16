package com.infix.album.pages

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.infix.album.R
import com.infix.album.adapter.UsersListAdapter
import com.infix.album.api.AppApi
import com.infix.album.databinding.ActivityMainBinding
import com.infix.album.models.User
import com.infix.album.util.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUsers()
        log("Oncreate")
    }

    private fun getUsers() {
        AppApi().getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) setUsersList(response)
                else showError(getString(R.string.try_again))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showError(t.localizedMessage!!)
            }
        })
    }

    private fun setUsersList(response: Response<List<User>>) {
        binding.apply {
            rvUsers.adapter = UsersListAdapter(response.body()!!)
            pbHome.visibility = View.GONE
            rvUsers.visibility = View.VISIBLE
        }
    }


    private fun showError(error: String) {
        binding.apply {
            tvError.text = error
            tvError.visibility = View.VISIBLE
            pbHome.visibility = View.GONE
        }
    }


}