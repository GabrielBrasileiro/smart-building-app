package com.universodoandroid.smartbuilding.module.menu.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.universodoandroid.smartbuilding.Constants
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.SensorsDialogBinding
import com.universodoandroid.smartbuilding.extensions.numberOfColumns
import com.universodoandroid.smartbuilding.module.menu.dto.SensorDto
import com.universodoandroid.smartbuilding.remote.api.InjectionApiDataSourceMain

class SensorsDialogFragment : DialogFragment(), SensorsDialogContract.View, SensorsDialogContract.Handler {

    private var binding: SensorsDialogBinding? = null
    private var presenter: SensorsDialogPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SensorsDialogPresenter(this, InjectionApiDataSourceMain.provideApartmentApiDataSource())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sensors_dialog, container,false)
        binding?.handler = this

        dialog.setTitle("Controle")

        initRequest()

        return binding?.root
    }

    private fun initRequest() {
        showProgressBar(View.VISIBLE)

        val apartmentId = arguments?.getString(Constants.APARTMENT_ID_KEY)
        apartmentId?.let { presenter?.getSensors(apartmentId = it) }
    }

    override fun showSensors(sensors: List<SensorDto>) {
        showProgressBar(View.GONE)

        binding?.sensorsRecyclerView?.run {
            layoutManager = GridLayoutManager(context, resources.numberOfColumns())
            adapter = SensorsDialogAdapter(sensors) { changed ->

            }
        }
    }

    override fun showError(error: String) {
        showProgressBar(View.GONE)
        println(error)
    }

    private fun showProgressBar(visibility: Int) {
        binding?.progressBar?.visibility = visibility
    }

    override fun onClose(view: View) {
        dismiss()
    }


}