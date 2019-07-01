package com.universodoandroid.smartbuilding.module.menu.dialogs

import android.annotation.SuppressLint
import com.universodoandroid.smartbuilding.domain.Sensor
import com.universodoandroid.smartbuilding.extensions.toBool
import com.universodoandroid.smartbuilding.module.menu.dto.SensorDto
import com.universodoandroid.smartbuilding.remote.services.ApartmentApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class SensorsPresenter(private val view: SensorsContract.View,
                       private val apartmentApiDataSource: ApartmentApiDataSource) {

    fun getSensors(apartmentId: String) {
        apartmentApiDataSource.getApartment(id = apartmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ apartmentResponse ->
                    view.showSensors(apartmentResponse.sensors.map {
                        val pivot = it.pivot
                        SensorDto(pivot.sensorId, it.name, pivot.isOn.toBool())
                    })
                }) {
                    view.showError(it.localizedMessage)
                }
    }

    fun updateSensor(id: Int, sensor: Sensor, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        apartmentApiDataSource.updateSensor(id.toString(), sensor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(it)
                }) {
                    onError(it.localizedMessage)
                }
    }

}