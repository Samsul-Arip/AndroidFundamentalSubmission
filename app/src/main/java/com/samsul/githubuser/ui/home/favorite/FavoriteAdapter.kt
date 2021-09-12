package com.samsul.githubuser.ui.home.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.githubuser.data.database.UserEntity
import com.samsul.githubuser.data.model.DataSearchUser
import com.samsul.githubuser.databinding.ItemUserBinding
import com.samsul.githubuser.ui.home.MainAdapter
import com.squareup.picasso.Picasso

class FavoriteAdapter(val context: Context, val listFavorite: ArrayList<UserEntity>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setListfavorite(data: List<UserEntity>) {
        listFavorite.clear()
        listFavorite.addAll(data)
        notifyDataSetChanged()
    }


    inner class FavoriteViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserEntity) {
            binding.root.setOnClickListener {
                onItemClickCallBack?.onItemClick(data)
            }
            binding.apply {
                usernameList.text = data.name
                Picasso.get().load(data.image).into(imageList)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = listFavorite.size

    interface OnItemClickCallBack {
        fun onItemClick(data: UserEntity)
    }
}