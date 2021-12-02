package com.example.newapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView: MvpView {
    fun init()
    fun updateList()
    fun showError(message: Throwable)
}