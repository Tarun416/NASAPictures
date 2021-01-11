package com.example.nasapictures.presentation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasapictures.R
import com.example.nasapictures.domain.model.Picture
import com.example.nasapictures.model.Pictures
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(private val context: Context, private val onPicClick: OnPicClick, private val planetaryResponse : MutableList<Picture> = mutableListOf() ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return planetaryResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context)
           .load(planetaryResponse[position].url)
           .placeholder(R.drawable.placeholder)
           .centerCrop()
           .into(holder.picture)

        holder.picture.setOnClickListener{onPicClick.onPicClick(position)}
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val picture = itemView.img
    }

   
    interface OnPicClick
    {
        fun onPicClick(pos : Int)
    }

}