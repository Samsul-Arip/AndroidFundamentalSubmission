package com.samsul.githubuser.ui.home.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.samsul.githubuser.R
import com.samsul.githubuser.ui.home.detail.fragment.followers.FollowerFragment
import com.samsul.githubuser.ui.home.detail.fragment.following.FollowingFragment

class SectionPagerAdapter (val context: Context, val fm: FragmentManager, data: Bundle)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundle: Bundle = data

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tabs_1, R.string.tabs_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

}