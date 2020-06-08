package com.teo.recipes.meal.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.distinctUntilChanged
import com.teo.hospitalapp.data.MealRemoteDataSource
import com.teo.recipes.data.resultLiveData
import com.teo.recipes.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
@OpenForTesting
class MealRepository @Inject constructor(private val dao: MealDao,
                                         private val mealRemoteDataSource: MealRemoteDataSource
) {

    fun observeMeal(idMeal: Int) = resultLiveData(
            databaseQuery = { dao.getMeal(idMeal) },
            networkCall = { mealRemoteDataSource.fetchMeal(idMeal) },
            saveCallResult = { it.meals?.get(0)?.let { it1 -> dao.update(it1) } })
            .distinctUntilChanged()

    @RequiresApi(Build.VERSION_CODES.N)
    fun observeMealsByCategory(strCategory: String) = resultLiveData(
            databaseQuery = { dao.getMealsByCategory(strCategory) },
            networkCall = { mealRemoteDataSource.fetchMeals(strCategory) }
    ) {
        it.meals?.let { it2 -> dao.insertAll(it2) }
        it.meals?.let { dao.updateAll(strCategory) }
    }


    companion object {


        // For Singleton instantiation
        @Volatile
        private var instance: MealRepository? = null

        fun getInstance(dao: MealDao, mealRemoteDataSource: MealRemoteDataSource) =
                instance ?: synchronized(this) {
                    instance
                            ?: MealRepository(dao, mealRemoteDataSource).also { instance = it }
                }
    }


}



