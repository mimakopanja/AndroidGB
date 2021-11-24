package com.example.newapp.mvp.view

import com.example.newapp.mvp.model.entity.GithubRepository
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView: MvpView {
    fun fillData(data: GithubRepository)
}