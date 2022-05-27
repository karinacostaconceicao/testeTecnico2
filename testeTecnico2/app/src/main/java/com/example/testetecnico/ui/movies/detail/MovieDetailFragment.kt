package com.example.testetecnico.ui.movies.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.testetecnico.commons.Status
import com.example.testetecnico.databinding.FragmentMoviesDetailsBinding
import com.example.testetecnico.databinding.FragmentMoviesReleaseBinding
import com.example.testetecnico.repositories.entities.Movie
import com.example.testetecnico.ui.movies.list.adapter.MoviesReleasesAdapter
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadMovie(arguments)
    }

    private fun setupObservers() {
        viewModel.title.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it
        }
        viewModel.description.observe(viewLifecycleOwner) {
            binding.tvDescriptionTitle.text = it
        }
        viewModel.raiting.observe(viewLifecycleOwner) {
            binding.rbRatingBar.rating = it
        }
        viewModel.urlImage.observe(viewLifecycleOwner) {
            binding.imageMovie.load(it)
        }
        viewModel.language.observe(viewLifecycleOwner) {
            binding.tvLanguage.text = it
        }
        viewModel.isAdult.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvAdultCardView.visibility = View.VISIBLE
            } else {
                binding.tvAdultCardView.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}