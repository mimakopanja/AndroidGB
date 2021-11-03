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
        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }
        with(binding){
            btnCounter1.setOnClickListener(listener)
            btnCounter2.setOnClickListener(listener)
            btnCounter3.setOnClickListener(listener)
        }
    }

    override fun setButtonText(index: Int, text: String) = with(binding) {
        when(index){
            0 -> btnCounter1.text = text
            1 -> btnCounter2.text = text
            2 -> btnCounter3.text = text
        }
    }
}