package com.universodoandroid.smartbuilding.module.apartments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.universodoandroid.smartbuilding.Constants
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.SmartBuildingApplication
import com.universodoandroid.smartbuilding.databinding.ActivityMenuBinding
import com.universodoandroid.smartbuilding.extensions.numberOfColumns
import com.universodoandroid.smartbuilding.module.login.LoginActivity
import com.universodoandroid.smartbuilding.module.sensors.SensorsActivity
import com.universodoandroid.smartbuilding.module.apartments.dto.ApartmentDto
import com.universodoandroid.smartbuilding.remote.api.InjectionApiDataSourceMain

class ApartmentActivity : AppCompatActivity(), ApartmentContract.View {

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
        val intent = Intent(this, SensorsActivity::class.java)
        intent.putExtra(Constants.APARTMENT_ID_KEY, id)

        startActivity(intent)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        redirectToLogin()
    }

    override fun showLoader() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun dismissLoader() {
        binding?.progressBar?.visibility = View.GONE
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        SmartBuildingApplication.session?.deleteStateLogin()
        finish()
    }

}