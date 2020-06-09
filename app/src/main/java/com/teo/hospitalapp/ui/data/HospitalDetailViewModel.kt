package com.teo.hospitalapp.ui.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.teo.hospitalapp.data.HospitalRepository
import javax.inject.Inject
import kotlin.properties.Delegates

@RequiresApi(Build.VERSION_CODES.N)
class HospitalDetailViewModel @Inject constructor(repository: HospitalRepository) : ViewModel() {

    var orgId by Delegates.notNull<Int>()
    val hospital by lazy { repository.observeHospital(orgId) }
}