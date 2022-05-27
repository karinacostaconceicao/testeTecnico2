package com.example.testetecnico.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testetecnico.R
import com.example.testetecnico.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController()
        setupEvents()
        setupObservers()
    }

    private fun setupEvents() {
        binding.loginButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }
    }

    private fun setupObservers() {
        viewModel.messageError.observe(viewLifecycleOwner) {
            binding.tvMessageError.text = it
            binding.tvMessageError.visibility = if (it.isEmpty()) { View.GONE } else { View.VISIBLE }
        }
        viewModel.isLoginSuccess.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.login_to_movies)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}