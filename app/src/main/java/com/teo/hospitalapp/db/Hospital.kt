package com.teo.hospitalapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hospital (
    val Address1: String,
    val Address2: String,
    val Address3: String,
    val City: String,
    val County: String,
    val Email: String,
    val Fax: String,
    val IsPimsManaged: String,
    val Latitude: Double,
    val Longitude: Double,
    val OrganisationCode: String,
    @PrimaryKey
    val OrganisationID: Int,
    val OrganisationName: String,
    val OrganisationStatus: String,
    val OrganisationType: String,
    val ParentName: String,
    val ParentODSCode: String,
    val Phone: String,
    val Postcode: String,
    val Sector: String,
    val SubType: String,
    val Website: String
)