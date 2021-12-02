package com.example.newapp


import android.os.Bundle
import android.view.MenuItem
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.mvp.presenter.MainPresenter
import com.example.newapp.mvp.view.MainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var viewBinding: ActivityMainBinding

    @Inject lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when( item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        } else -> super.onOptionsItemSelected(item)
    }
}