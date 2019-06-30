package com.universodoandroid.smartbuilding.module.menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.universodoandroid.smartbuilding.Constants
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.ActivityMenuBinding
import com.universodoandroid.smartbuilding.extensions.numberOfColumns
import com.universodoandroid.smartbuilding.module.menu.dialogs.SensorsDialogFragment
import com.universodoandroid.smartbuilding.module.menu.dto.ApartmentDto
import com.universodoandroid.smartbuilding.remote.api.InjectionApiDataSourceMain

class ApartmentActivity : AppCompatActivity(), ApartmentContract.Activity {

    private var presenter: ApartmentPresenter? = null
    private var binding: ActivityMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)
        presenter = ApartmentPresenter(this, InjectionApiDataSourceMain.provideApartmentApiDataSource())

        presenter?.getApartments()
    }

    override fun showApartments(apartments: List<ApartmentDto>) {
        binding?.apartmentsRecyclerView?.run {
            layoutManager = GridLayoutManager(applicationContext, resources.numberOfColumns())
            adapter = ApartmentAdapter(apartments) { id ->
                showControlDialog(id = id)
            }
        }
    }

    private fun showControlDialog(id: Int) {
        val bundle = Bundle().apply {
            putString(Constants.APARTMENT_ID_KEY, id.toString())
        }

        val sensorsDialog = SensorsDialogFragment()
        sensorsDialog.arguments = bundle

        sensorsDialog.show(supportFragmentManager, sensorsDialog.tag)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

}