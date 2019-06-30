package com.universodoandroid.smartbuilding.module.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.ActivityLoginBinding
import com.universodoandroid.smartbuilding.domain.User
import com.universodoandroid.smartbuilding.local.Session
import com.universodoandroid.smartbuilding.module.menu.ApartmentActivity
import com.universodoandroid.smartbuilding.remote.api.InjectionApiDataSourceMain

class LoginActivity : AppCompatActivity(), LoginHandler, LoginContract.Activity {

    private var binding: ActivityLoginBinding? = null
    private var presenter: LoginPresenter? = null
    private var session: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        session = Session(this)
        presenter = LoginPresenter(this, InjectionApiDataSourceMain.provideAuthApiDataSource())
    }

    override fun auth(user: User) {
        presenter?.login(user)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.handler = this
        binding?.user = User()
    }

    override fun saveToken(token: String) {
        session?.saveStateLoginSuccess(token)
        redirectToMenu()
    }

    override fun onError(error: String) {
        print(error)
    }

    private fun redirectToMenu() {
        startActivity(Intent(this, ApartmentActivity::class.java))
    }

}
