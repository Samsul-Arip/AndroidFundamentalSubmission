package com.samsul.githubuser.ui.home.detail.fragment.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.githubuser.R
import com.samsul.githubuser.databinding.FragmentFollowerBinding
import com.samsul.githubuser.databinding.FragmentFollowingBinding
import com.samsul.githubuser.ui.home.detail.DetailUserActivity
import com.samsul.githubuser.ui.home.detail.fragment.followers.FollowerAdapter
import com.samsul.githubuser.ui.home.detail.fragment.followers.FollowersViewModel

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var followingAdapter: FollowingAdapter
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        followingAdapter = FollowingAdapter(requireContext(), arrayListOf())
        followingAdapter.notifyDataSetChanged()
        binding.rvFollowing.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = followingAdapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, { items ->
            if(items != null) {
                followingAdapter.setListUser(items)
                showLoading(false)
            }
        })

    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.pbLoading.visibility = View.VISIBLE
                binding.rvFollowing.visibility = View.GONE
            }
            false -> {
                binding.pbLoading.visibility = View.GONE
                binding.rvFollowing.visibility = View.VISIBLE
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}