package com.teo.hospitalapp.ui.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.teo.hospitalapp.data.HospitalRepository
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
class HospitalViewModel @Inject constructor(repository: HospitalRepository) : ViewModel() {


    val hospitals by lazy { repository.observeHospitals() }
}