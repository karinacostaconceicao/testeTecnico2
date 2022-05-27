package com.example.testetecnico.ui.movies.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testetecnico.databinding.MoviesReleaseItemBinding
import com.example.testetecnico.repositories.entities.Movie

class MoviesReleasesAdapter(private val items: List<Movie>): RecyclerView.Adapter<MoviesReleasesAdapter.MoviesReleasesAdapterHolder>() {

    var onSelected: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesReleasesAdapterHolder {
        val binding = MoviesReleaseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesReleasesAdapterHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MoviesReleasesAdapterHolder, position: Int) {
        val movie = items[position]
        holder.binding.imageMovie.load(movie.urlImage())
        holder.binding.tvTitle.text = movie.title
        holder.binding.tvDescriptionTitle.text = movie.overview
        holder.binding.rbRatingBar.rating = movie.voteAverage
        holder.binding.clItem.setOnClickListener {
            onSelected?.invoke(movie)
        }
    }

    inner class MoviesReleasesAdapterHolder(val binding: MoviesReleaseItemBinding) :RecyclerView.ViewHolder(binding.root)
}