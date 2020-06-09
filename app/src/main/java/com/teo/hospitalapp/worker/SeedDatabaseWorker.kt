package com.teo.hospitalapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.teo.hospitalapp.data.AppDatabase
import com.teo.hospitalapp.data.Hospital
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import com.teo.hospitalapp.parse.Parser

class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open("Hospital.csv").use {intputStream ->

                        val list: List<Hospital> = Parser().toDataSet(intputStream.reader())

                        AppDatabase.getInstance(applicationContext).hospitalDao().insertAll(list)

                        Result.success()
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error seeding database")
                Result.failure()
            }
        }
    }
}