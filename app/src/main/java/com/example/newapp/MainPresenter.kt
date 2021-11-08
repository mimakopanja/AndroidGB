package com.example.newapp

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    fun firstCounterOnClick() {
        val nextValue = model.next(0)
        view.setButtonFirstText(nextValue.toString())
    }

    fun secondCounterOnClick() {
        val nextValue = model.next(1)
        view.setButtonSecondText(nextValue.toString())
    }

    fun thirdCounterOnClick() {
        val nextValue = model.next(2)
        view.setButtonThirdText(nextValue.toString())
    }
}