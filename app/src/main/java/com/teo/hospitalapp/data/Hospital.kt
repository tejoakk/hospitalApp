package com.teo.hospitalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital")
data class Hospital(
    val address1: String? = null,
    val address2: String? = null,
    val address3: String? = null,
    val city: String? = null,
    val county: String? = null,
    val email: String? = null,
    val fax: String? = null,
    val isPimsManaged: Boolean? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val organisationCode: String,
    @PrimaryKey
    val organisationID: Int,
    val organisationName: String? = null,
    val organisationStatus: String? = null,
    val organisationType: String? = null,
    val parentName: String? = null,
    val parentODSCode: String? = null,
    val phone: String? = null,
    val postcode: String? = null,
    val sector: String? = null,
    val subType: String? = null,
    val website: String? = null
)

enum class Sector {
    NHS, Independent
}

enum class SubType {
    Hospital, MentalHealth, UNKNOWN
}