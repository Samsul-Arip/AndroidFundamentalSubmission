package com.samsul.githubuser.ui.home.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.samsul.githubuser.R
import com.samsul.githubuser.databinding.ActivityDetailUserBinding
import com.squareup.picasso.Picasso

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this, { items ->
            if(items != null) {
                binding.apply {
                    tvUsername.text = items.username
                    tvName.text = items.name
                    tvFollowers.text = "Followers : ${items.followers}"
                    tvFollowing.text = "Following : ${items.following}"
                    Picasso.get()
                        .load(items.image)
                        .into(imageDetail)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}


