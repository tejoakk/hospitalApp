package com.teo.hospitalapp.util

import com.teo.hospitalapp.data.Hospital


object DomainObjectFactory {

    fun createMeal() = Hospital(organisationID = 21232, organisationName = "Test1",fax = "1231231", website = "12312",
        email = "test1", phone = "sdsaasd",parentName = "",parentODSCode = "",longitude = "",
        latitude = "",postcode = "",county = "",city = "",address2 = "",address1 = "",isPimsManaged = false,organisationStatus = "",
        sector = "",subType = "",organisationType = "",address3 = "",organisationCode = "")

    fun createMeal(count: Int): List<Hospital> {
        return (0 until count).map {
            createMeal()
        }
    }

}
