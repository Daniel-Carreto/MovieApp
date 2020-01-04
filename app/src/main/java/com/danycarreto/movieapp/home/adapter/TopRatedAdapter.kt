package com.danycarreto.movieapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danycarreto.movieapp.R
import com.danycarreto.movieapp.home.data.model.TopResults

class TopRatedAdapter(val topRateList:List<TopResults>,
                      private val onItemClickListener: OnItemClickListener<TopResults>) :
    RecyclerView.Adapter<TopRatedAdapter.TopViewHolder>(), Filterable{

    private var topFilterList: List<TopResults> = topRateList

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val query = charSequence.toString().toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if(query.isEmpty()){
                    topRateList
                }else{
                    topRateList.filter {
                        it.title.toLowerCase().contains(query)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                topFilterList = results?.values as List<TopResults>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_top_rated, parent, false) as View
        return TopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topFilterList.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClick(topFilterList[position], position)
        }
        holder.tvTitle.text = topFilterList[position].title
        holder.tvOverview.text = topFilterList[position].overview
        holder.tvDate.text = topFilterList[position].releaseDate
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w200${topFilterList[position].poster}")
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