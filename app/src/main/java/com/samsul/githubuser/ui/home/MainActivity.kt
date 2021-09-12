package com.samsul.githubuser.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.githubuser.R
import com.samsul.githubuser.data.model.DataSearchUser
import com.samsul.githubuser.databinding.ActivityMainBinding
import com.samsul.githubuser.ui.home.detail.DetailUserActivity
import com.samsul.githubuser.ui.home.favorite.FavoriteActivity
import com.samsul.githubuser.ui.home.setting.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = MainAdapter(this, arrayListOf())
        userAdapter.notifyDataSetChanged()
        userAdapter.setOnItemClickCallBack(object: MainAdapter.OnItemClickCallBack{
            override fun onItemClick(data: DataSearchUser.dataUser) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.name)
                intent.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                intent.putExtra(DetailUserActivity.EXTRA_IMAGE, data.image)
                startActivity(intent)
            }

        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvList.apply {
               layoutManager = LinearLayoutManager(this@MainActivity)
               setHasFixedSize(true)
               adapter = userAdapter
            }
            edtSearch.setOnEditorActionListener { view, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchUser()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        }
        viewModel.getSearchUser().observe(this, { list ->
            if(list != null) {
                userAdapter.setListUser(list)
                showLoading(false)
            } else {
                showLoading(false)
            }
        })

        supportActionBar?.title = "Alarm Reminder"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            } else -> false
        }
    }

    private fun searchUser() {
        binding.apply {
            val edtUsername = edtSearch.text.toString()
            if(edtUsername.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUser(edtUsername)
        }
    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.pbLoading.visibility = View.VISIBLE
                binding.rvList.visibility = View.GONE
            }
            false -> {
                binding.pbLoading.visibility = View.GONE
                binding.rvList.visibility = View.VISIBLE
            }
        }
    }
}