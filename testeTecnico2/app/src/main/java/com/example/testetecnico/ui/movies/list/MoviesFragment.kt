package com.example.testetecnico.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testetecnico.R
import com.example.testetecnico.commons.Status
import com.example.testetecnico.databinding.FragmentMoviesReleaseBinding
import com.example.testetecnico.repositories.entities.Movie
import com.example.testetecnico.ui.movies.list.adapter.MoviesReleasesAdapter
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesReleaseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesReleaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
        loadMovies()
    }

    private fun loadMovies() {
        viewModel.upcomings()
    }

    private fun setupObservers() {
        viewModel.upcomings().observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        hideLoading()
                        result.data?.let { movies -> loadMovies(movies) }
                    }
                    Status.ERROR -> {
                        hideLoading()
                    }
                    Status.LOADING -> {
                        showLoading()
                    }
                }
            }
        }
    }

    private fun loadMovies(movies: ArrayList<Movie>) {
        val adapter = MoviesReleasesAdapter(movies)
        adapter.onSelected = { movie ->
            val bundle = bundleOf("movie" to Gson().toJson(movie))
            findNavController().navigate(R.id.movies_to_detail, bundle)
        }
        binding.rvMoviesRelease.adapter = adapter
    }

    private fun setupViews() {
        binding.rvMoviesRelease.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMoviesRelease.setHasFixedSize(true)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvMoviesRelease.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.rvMoviesRelease.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}