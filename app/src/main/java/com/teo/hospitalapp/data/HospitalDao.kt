package com.teo.hospitalapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Data Access Object for the Hospital class.
 */
@Dao
interface HospitalDao {


    @Query("SELECT * FROM hospital WHERE OrganisationID = :organisationID")
    suspend fun getHospital(organisationID: Int): LiveData<Hospital>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<Hospital>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: Hospital)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(meal: Hospital)

}
