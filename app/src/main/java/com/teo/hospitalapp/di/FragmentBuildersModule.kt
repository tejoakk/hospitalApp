package com.teo.hospitalapp.di


import com.teo.hospitalapp.ui.main.HospitalDetailFragment
import com.teo.hospitalapp.ui.main.HospitalFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHospitalFragment(): HospitalFragment

    @ContributesAndroidInjector
    abstract fun contributeHospitalDetailFragment(): HospitalDetailFragment

}
