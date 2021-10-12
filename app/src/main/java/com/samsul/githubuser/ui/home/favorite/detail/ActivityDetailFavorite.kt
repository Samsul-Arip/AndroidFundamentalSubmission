package com.samsul.githubuser.ui.home.favorite.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.samsul.githubuser.databinding.ActivityDetailFavoriteBinding
import com.samsul.githubuser.ui.home.detail.DetailUserActivity
import com.samsul.githubuser.ui.home.detail.SectionPagerAdapter
import com.squareup.picasso.Picasso

class ActivityDetailFavorite : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME_DETAIL = "username_detail"
    }
    private lateinit var viewModel: DetailFavoriteViewModel
    private lateinit var binding: ActivityDetailFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME_DETAIL)
        val bundle = Bundle()
        bundle.putString(DetailUserActivity.EXTRA_USERNAME, username)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailFavoriteViewModel::class.java)

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


        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }


        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}