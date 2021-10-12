package com.samsul.consumerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.consumerapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var favoriteAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        favoriteAdapter = MainAdapter(this, arrayListOf())

        binding.rvFavorite.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = favoriteAdapter
        }
        viewModel.setFavoriteUser(this)
        viewModel.getFavoriteUser().observe(this, {
            if(it != null) {
                favoriteAdapter.setListfavorite(it)
            }
        })



        supportActionBar?.title = "Favorite User"
    }

}