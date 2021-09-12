package com.samsul.githubuser.ui.home.detail.fragment.followers


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.githubuser.data.model.DataFollow
import com.samsul.githubuser.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class FollowerAdapter(val context: Context, val listUser: ArrayList<DataFollow>)
    : RecyclerView.Adapter<FollowerAdapter.MainViewHolder>(){

    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setListUser(users: ArrayList<DataFollow>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataFollow) {
            binding.root.setOnClickListener {
                onItemClickCallBack?.onItemClick(data)
            }
            binding.apply {
                usernameList.text = data.username
                Picasso.get()
                    .load(data.image)
                    .into(imageList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listUser[position])

    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallBack {
        fun onItemClick(data: DataFollow)
    }


}