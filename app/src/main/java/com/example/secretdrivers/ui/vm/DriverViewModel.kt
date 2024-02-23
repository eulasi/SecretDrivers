package com.example.secretdrivers.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretdrivers.data.db.sddriver.DriverDao
import com.example.secretdrivers.data.db.sddriver.DriverEntity
import com.example.secretdrivers.data.db.sddriver.RouteDao
import com.example.secretdrivers.data.db.sddriver.RouteEntity
import com.example.secretdrivers.data.model.driver.Driver
import com.example.secretdrivers.data.model.driver.DriverModel
import com.example.secretdrivers.data.model.driver.Route
import com.example.secretdrivers.data.remote.ApiDetails
import com.example.secretdrivers.data.remote.ApiService
import com.example.secretdrivers.data.repository.SdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val service: ApiService,
    private val driverDao: DriverDao,
    private val repository: SdRepository
) : ViewModel() {

    private val _driverModels = MutableStateFlow<List<Driver>>(emptyList())
    val driverModels: StateFlow<List<Driver>> = _driverModels

    private val _routes = MutableStateFlow<List<Route>>(emptyList())
    val routes: StateFlow<List<Route>> = _routes

    init {
        getDriverAndRoutes()
    }

    private fun getDriverAndRoutes() {
        viewModelScope.launch {
        //    val driverModel = repository.getAllDrivers()
            val driverModel = service.getDriverAndRoutes()

            // Update drivers
            val driversList = driverModel.drivers
            _driverModels.value = driversList

            // Update routes
            val routesList = driverModel.routes
            _routes.value = routesList
        }
    }

    fun sortDriversByLastName() {
        _driverModels.value = _driverModels.value.sortedBy { it.name?.split(" ")?.lastOrNull() }
    }

}