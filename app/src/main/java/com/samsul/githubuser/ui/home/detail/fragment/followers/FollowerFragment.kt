package com.samsul.githubuser.ui.home.detail.fragment.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.githubuser.databinding.FragmentFollowerBinding
import com.samsul.githubuser.ui.home.detail.DetailUserActivity

class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var followerAdapter: FollowerAdapter
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        followerAdapter = FollowerAdapter(requireContext(), arrayListOf())
        followerAdapter.notifyDataSetChanged()
        binding.rvFollowers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = followerAdapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, { items ->
            if(items != null) {
                followerAdapter.setListUser(items)
                showLoading(false)
            }
        })

    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.pbLoading.visibility = View.VISIBLE
                binding.rvFollowers.visibility = View.GONE
            }
            false -> {
                binding.pbLoading.visibility = View.GONE
                binding.rvFollowers.visibility = View.VISIBLE
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}