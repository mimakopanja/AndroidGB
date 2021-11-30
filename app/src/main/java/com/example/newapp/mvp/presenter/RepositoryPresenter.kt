package com.example.newapp.mvp.presenter

import com.example.newapp.BackButtonListener
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(
    private val repository: GithubRepository,
    private val router: Router
): MvpPresenter<RepositoryView>(), BackButtonListener {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.fillData(repository)
    }
    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}