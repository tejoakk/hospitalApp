package com.teo.hospitalapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teo.hospitalapp.ui.data.HospitalDetailViewModel
import com.teo.hospitalapp.ui.data.HospitalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HospitalViewModel::class)
    abstract fun bindHospitalViewModel(viewModel: HospitalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HospitalDetailViewModel::class)
    abstract fun bindHospitalViewModel(viewModel: HospitalDetailViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
