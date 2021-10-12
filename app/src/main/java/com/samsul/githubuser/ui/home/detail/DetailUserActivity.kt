package com.samsul.githubuser.ui.home.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.samsul.githubuser.data.database.UserEntity
import com.samsul.githubuser.databinding.ActivityDetailUserBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "username"
        const val EXTRA_USERNAME_DETAIL = "username_detail"
        const val EXTRA_ID = "id"
        const val EXTRA_IMAGE = "image"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        var id = intent.getStringExtra(EXTRA_ID)
        val _id = Integer.parseInt(id)

        val image = intent.getStringExtra(EXTRA_IMAGE)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
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

        if (image != null) {
            saveFavoriteUser(_id, username, image)
        }


        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }


        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun saveFavoriteUser(id: Int, name: String, image: String) {
        binding.fabFavorite.setOnClickListener {
            viewModel.addFavoriteUser(this, id, name, image) {
                successSaveFavorite()
            }
        }
    }

    private fun successSaveFavorite() {
        Toast.makeText(this, "Berhasil ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}


