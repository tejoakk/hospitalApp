package com.teo.hospitalapp.di

import com.teo.hospitalapp.MainActivity
import com.teo.hospitalapp.di.FragmentBuildersModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
