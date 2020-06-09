package com.teo.hospitalapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.teo.hospitalapp.parse.Parser
import com.teo.hospitalapp.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
@OpenForTesting
class HospitalRepository @Inject constructor(private val dao: HospitalDao,
                                         private val hospitalRemoteDataSource: HospitalRemoteDataSource
) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun observeHospitals() = resultLiveData(
            databaseQuery = { dao.getHospitals() },
            networkCall = { hospitalRemoteDataSource.fetchData() }
    ) {
        it.byteStream().use{inputStream ->
            val list: List<Hospital> = Parser().toDataSet(inputStream.reader())
            dao.insertAll(list)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
     fun observeHospital(orgId: Int) = dao.getHospital(orgId)




//    companion object {
//
//
//        // For Singleton instantiation
//        @Volatile
//        private var instance: HospitalRepository? = null
//
//        fun getInstance(dao: HospitalDao, hospitalRemoteDataSource: HospitalRemoteDataSource) =
//                instance ?: synchronized(this) {
//                    instance
//                            ?: HospitalDao(dao, hospitalRemoteDataSource).also { instance = it }
//                }
//    }


}



