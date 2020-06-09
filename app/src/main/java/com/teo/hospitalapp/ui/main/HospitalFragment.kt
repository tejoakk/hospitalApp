package com.teo.hospitalapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.teo.hospitalapp.R
import com.teo.hospitalapp.databinding.HospitalFragmentBinding
import com.teo.hospitalapp.di.Injectable
import com.teo.hospitalapp.di.injectViewModel
import com.teo.hospitalapp.ui.data.HospitalAdapter
import com.teo.hospitalapp.ui.data.HospitalViewModel
import com.teo.hospitalapp.ui.dec.VerticalItemDecoration
import com.teo.hospitalapp.ui.dec.hide
import com.teo.hospitalapp.ui.dec.show
import javax.inject.Inject

class HospitalFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HospitalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = HospitalFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = HospitalAdapter()
        binding.recyclerView.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true)
        )
        binding.recyclerView.adapter = adapter

        viewModel = injectViewModel(viewModelFactory)
        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: HospitalFragmentBinding, adapter: HospitalAdapter) {
        viewModel.hospitals.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                com.teo.hospitalapp.data.Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
                }
                com.teo.hospitalapp.data.Result.Status.LOADING -> binding.progressBar.show()
                com.teo.hospitalapp.data.Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}