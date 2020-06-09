package com.teo.hospitalapp.ui.data

import androidx.lifecycle.ViewModel
import com.teo.hospitalapp.data.HospitalRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class HospitalDetailViewModel @Inject constructor(repository: HospitalRepository) : ViewModel() {

    var orgId by Delegates.notNull<Int>()
    //var prop:  Meal? = null
    val hospital by lazy { repository.observeHospital(orgId) }
}