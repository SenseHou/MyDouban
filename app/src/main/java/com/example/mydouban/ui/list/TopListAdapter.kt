package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopListRecycleItemBindingImpl
import com.example.mydouban.model.MovieSubject

class TopListAdapter() : RecyclerView.Adapter<TopListAdapter.TopListViewHolder>() {

    private val movies: MutableList<MovieSubject> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListViewHolder {
        return TopListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.top_list_recycle_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: TopListViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    fun updateData(newMovies: List<MovieSubject>) {
        this.movies.clear()
        this.movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    inner class TopListViewHolder(private val dataBinding: TopListRecycleItemBindingImpl) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("TopListViewHolder bind $movie")
            dataBinding.movieSubject = movie
        }
    }

}