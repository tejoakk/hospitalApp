package com.teo.hospitalapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital")
data class Hospital(
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val county: String,
    val email: String,
    val fax: String,
    val isPimsManaged: Boolean,
    val latitude: String,
    val longitude: String,
    val organisationCode: String,
    @PrimaryKey
    val organisationID: Int,
    val organisationName: String,
    val organisationStatus: String,
    val organisationType: String,
    val parentName: String,
    val parentODSCode: String,
    val phone: String,
    val postcode: String,
    val sector: Sector,
    val subType: SubType,
    val website: String
)
enum class Sector {
    NHS, Independent
}

enum class SubType {
    Hospital, MentalHealth, UNKNOWN
}