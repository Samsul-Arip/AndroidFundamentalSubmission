package com.samsul.githubuser.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.githubuser.data.DataSearchUser
import com.samsul.githubuser.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class MainAdapter(val context: Context, val listUser: ArrayList<DataSearchUser.dataUser>)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setListUser(users: ArrayList<DataSearchUser.dataUser>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataSearchUser.dataUser) {
            binding.root.setOnClickListener {
                onItemClickCallBack?.onItemClick(data)
            }
            binding.apply {
                usernameList.text = data.name
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
        fun onItemClick(data: DataSearchUser.dataUser)
    }


}