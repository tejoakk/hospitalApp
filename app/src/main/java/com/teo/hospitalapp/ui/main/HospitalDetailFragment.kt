package com.teo.hospitalapp.ui.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.teo.hospitalapp.R
import com.teo.hospitalapp.data.Hospital
import com.teo.hospitalapp.databinding.HospitalDetailsFragmentBinding
import com.teo.hospitalapp.di.Injectable
import com.teo.hospitalapp.di.injectViewModel
import com.teo.hospitalapp.ui.data.HospitalDetailViewModel
import com.teo.hospitalapp.ui.dec.hide
import com.teo.hospitalapp.ui.dec.setTitle
import com.teo.hospitalapp.ui.dec.show
import javax.inject.Inject

class HospitalDetailFragment:  Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HospitalDetailViewModel

    private val args: HospitalDetailFragmentArgs by navArgs()
    private lateinit var hospital: Hospital

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewModel = injectViewModel(viewModelFactory)
//        viewModel.prop = MealDetailFragmentArgs.fromBundle(arguments!!).hospital
        viewModel.orgId = args.orgId

        val binding = DataBindingUtil.inflate<HospitalDetailsFragmentBinding>(
            inflater, R.layout.hospital_details_fragment, container, false).apply {
            lifecycleOwner = this@HospitalDetailFragment
        }

        subscribeUi(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    private fun subscribeUi(binding: HospitalDetailsFragmentBinding) {
        viewModel.hospital.observe(viewLifecycleOwner, Observer { result ->
            when (result) { result ->
            { binding.progressBar.hide()
                    result?.let { bindView(binding, it) }
                }
//                com.teo.hospitalapp.data.Result.Status.LOADING -> binding.progressBar.show()
//                com.teo.hospitalapp.data.Result.Status.ERROR -> {
//                    binding.progressBar.hide()
//                    Snackbar.make(binding.progressBar, result.message!!, Snackbar.LENGTH_LONG).show()
//                }
            }
        })
    }

    private fun bindView(binding: HospitalDetailsFragmentBinding, hospital: Hospital) {
        hospital.apply {
            setTitle("Hospital Details")
            binding.name.text = organisationName
            binding.type.text = subType
            binding.sector.text= sector
            binding.parentTitle.text=parentName
            binding.parentCode.text=parentODSCode
            val address : List<String?> = listOf(address1, address2, address3, city, county, postcode)
            binding.address1.text = address.joinToString ( separator = System.lineSeparator())
            binding.website.text = website
            binding.phone.text =phone
            binding.fax.text = fax
            this@HospitalDetailFragment.hospital = hospital
        }
    }
}