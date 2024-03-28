package com.affan.weather.app.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.affan.weather.app.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    lateinit var viewModel: DetailViewModel
    lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val detailFragmentArgs by navArgs<DetailFragmentArgs>()

        viewModelFactory = DetailViewModelFactory(detailFragmentArgs.weatherItem)
        viewModel = ViewModelProvider(this, viewModelFactory)[(DetailViewModel::class.java)]
        binding.viewModel = viewModel

        return binding.root
    }
}