package com.samsul.githubuser.ui.home.favorite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.githubuser.R
import com.samsul.githubuser.data.database.UserEntity
import com.samsul.githubuser.databinding.ActivityFavoriteBinding
import com.samsul.githubuser.ui.home.favorite.detail.ActivityDetailFavorite

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAdapter = FavoriteAdapter(this, arrayListOf())

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FavoriteViewModel::class.java)
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
        viewModel.listUserFavorite.observe(this, {
            favoriteAdapter.setListfavorite(it)
        })
        viewModel.getAllFavorite(this)

        favoriteAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallBack{
            override fun onItemClick(data: UserEntity) {
                val options = arrayOf<CharSequence>(
                    "Hapus Data",
                    "Lihat Detail"
                )
                val builder : AlertDialog.Builder = AlertDialog.Builder(this@FavoriteActivity)
                builder.setTitle("What do you want?")
                builder.setItems(options, DialogInterface.OnClickListener { dialog, position ->
                    if(position == 0) {
                        viewModel.deleteFavorite(this@FavoriteActivity, data){
                            Toast.makeText(this@FavoriteActivity, "Dihapus dari favorite", Toast.LENGTH_SHORT).show()
                        }
                    } else if(position == 1) {
                        val intent = Intent(this@FavoriteActivity, ActivityDetailFavorite::class.java)
                        intent.putExtra(ActivityDetailFavorite.EXTRA_USERNAME_DETAIL, data.name)
                        startActivity(intent)
                    }
                })
                builder.show()
            }

        })
        binding.swipe.setOnRefreshListener {
            onRefresh(true)
            viewModel.getAllFavorite(this)
            onRefresh(false)
        }


        supportActionBar?.title = "Favorite User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_settings -> {
                true
            } else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun onRefresh(refresh: Boolean) {
        when(refresh) {
            true -> {
                binding.swipe.isRefreshing = true
            }
            false -> {
                binding.swipe.isRefreshing = false
            }
        }
    }
}