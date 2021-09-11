package com.samsul.githubuser.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.githubuser.data.DataSearchUser
import com.samsul.githubuser.databinding.ActivityMainBinding
import com.samsul.githubuser.ui.home.detail.DetailUserActivity

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