package com.teo.hospitalapp.di

import android.app.Application
import com.teo.hospitalapp.data.AppDatabase
import com.teo.hospitalapp.data.HospitalRemoteDataSource
import com.teo.hospitalapp.service.ApiService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(@API okhttpClient: OkHttpClient,
                          converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, ApiService::class.java)

    @Singleton
    @Provides
    fun provideHospitalRemoteDataSource(apiService: ApiService)
            = HospitalRemoteDataSource(apiService)

    @API
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
                .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideMealDao(db: AppDatabase) = db.mealDao()


    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
