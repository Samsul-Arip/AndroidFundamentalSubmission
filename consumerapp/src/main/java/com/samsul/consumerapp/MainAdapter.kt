package com.samsul.consumerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.consumerapp.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class MainAdapter(val context: Context, val listFavorite: ArrayList<DataSearchUser>) :
    RecyclerView.Adapter<MainAdapter.FavoriteViewHolder>() {

    fun setListfavorite(dataSearch: List<DataSearchUser>) {
        listFavorite.clear()
        listFavorite.addAll(dataSearch)
        notifyDataSetChanged()
    }


    inner class FavoriteViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSearch: DataSearchUser) {
            binding.apply {
                usernameList.text = dataSearch.name
                Picasso.get().load(dataSearch.image).into(imageList)
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

}