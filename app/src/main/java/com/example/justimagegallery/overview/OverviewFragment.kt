package com.example.justimagegallery.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.justimagegallery.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel : OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        System.err.println("onCreateView")

        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoListAdapter()

        viewModel.navigateToSecondPhoto.observe(viewLifecycleOwner, Observer {
            System.err.println("navigateToSecondPhoto")
            if (it == true) {
                binding.photosGrid.scrollToPosition(12)
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
