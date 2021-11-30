package com.example.newapp.mvp.presenter

import com.example.newapp.mvp.model.navigation.IScreens
import com.example.newapp.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


    class MainPresenter(
        val router: Router,
        val screens: IScreens
    ) : MvpPresenter<MainView>() {

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            router.replaceScreen(screens.users())
        }

        fun backClicked() {
            router.exit()
        }
    }
