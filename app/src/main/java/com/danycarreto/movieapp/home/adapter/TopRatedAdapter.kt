package com.danycarreto.movieapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.home.data.model.TopResults

class TopRatedAdapter(val topRateList:List<TopResults>) : RecyclerView.Adapter<TopRatedAdapter.TopViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_top_rated, parent, false) as View
        return TopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topRateList.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.tvTitle.text = topRateList[position].title
        holder.tvOverview.text = topRateList[position].overview
        holder.tvDate.text = topRateList[position].releaseDate
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200${topRateList[position].poster}")
            .into(holder.ivPoster)
    }


    class TopViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val ivPoster: ImageView
        var tvTitle:TextView
        val tvOverview:TextView
        val tvDate:TextView

        init{
            ivPoster = view.findViewById(R.id.ivPoster)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvOverview = view.findViewById(R.id.tvOverview)
            tvDate = view.findViewById(R.id.tvDate)
        }
    }
}