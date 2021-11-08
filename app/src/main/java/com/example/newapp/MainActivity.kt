package com.example.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main), MainView {

    private val binding: ActivityMainBinding by viewBinding()
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnCounter1.setOnClickListener { presenter.firstCounterOnClick()}
            btnCounter2.setOnClickListener { presenter.secondCounterOnClick() }
            btnCounter3.setOnClickListener { presenter.thirdCounterOnClick() }
        }
    }

    override fun setButtonFirstText(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButtonSecondText(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButtonThirdText(text: String) {
        binding.btnCounter3.text = text
    }
}