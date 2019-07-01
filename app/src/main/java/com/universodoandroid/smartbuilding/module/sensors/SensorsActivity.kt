package com.universodoandroid.smartbuilding.module.sensors

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.universodoandroid.smartbuilding.Constants
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.SensorsActivityBinding
import com.universodoandroid.smartbuilding.domain.Sensor
import com.universodoandroid.smartbuilding.extensions.numberOfColumns
import com.universodoandroid.smartbuilding.module.sensors.dto.SensorDto
import com.universodoandroid.smartbuilding.remote.api.InjectionApiDataSourceMain

class SensorsActivity : AppCompatActivity(), SensorsContract.View {

    private lateinit var binding: SensorsActivityBinding

    private var presenter: SensorsPresenter? = null
    private val apartmentId: Int? by lazy {
        intent.getIntExtra(Constants.APARTMENT_ID_KEY, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.sensors_activity)
        presenter = SensorsPresenter(this, InjectionApiDataSourceMain.provideApartmentApiDataSource())

        setupView()
        initRequest()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = resources.getString(R.string.control_title)
    }

    private fun initRequest() {
        apartmentId?.let { presenter?.getSensors(apartmentId = it.toString()) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

    override fun showSensors(sensors: List<SensorDto>) {
        setupRecyclerView(sensors)
    }

    override fun showError(error: String) {
        println(error)
    }

    override fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun dismissLoader() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setupRecyclerView(sensors: List<SensorDto>) {
        binding.sensorsRecyclerView.run {
            layoutManager = GridLayoutManager(context, resources.numberOfColumns())
            adapter = SensorsAdapter(sensors) { id, isOn, onComplete, onError ->
                updateSensor(id, isOn, onComplete, onError)
            }
        }
    }

    private fun updateSensor(sensorId: Int, isOn: Boolean, onComplete: () -> Unit, onError: () -> Unit) {
        apartmentId?.let { apartmentId ->
            val sensor = Sensor(id = apartmentId, sensorId = sensorId, isOn = isOn)
            presenter?.updateSensor(apartmentId, sensor, {
                onComplete()
            }) {
                println(it)
                onError()
            }
        }
    }


}