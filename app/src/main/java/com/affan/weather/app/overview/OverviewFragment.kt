package com.affan.weather.app.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.affan.weather.app.adapter.WeatherItemAdapter
import com.affan.weather.app.adapter.WeatherItemListener
import com.affan.weather.app.database.WeatherItemDatabase
import com.affan.weather.app.databinding.FragmentOverviewBinding
import com.affan.weather.app.repository.Repository

class OverviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        val repository = Repository(WeatherItemDatabase.getDatabase(this.requireContext()))
        val viewModelFactory = OverviewViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[OverViewViewModel::class.java]
        val weatherItemAdapter = WeatherItemAdapter(WeatherItemListener {
            Navigation.findNavController(binding.root)
                .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.weatherItemAdapter = weatherItemAdapter

        setHasOptionsMenu(true)
        return binding.root
    }

}