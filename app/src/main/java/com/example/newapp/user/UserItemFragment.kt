package com.example.newapp.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newapp.BackButtonListener
import com.example.newapp.UsersView
import com.example.newapp.App
import com.example.newapp.data.USER_BUNDLE
import com.example.newapp.databinding.FragmentUserItemBinding
import com.example.newapp.screen.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserItemFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private lateinit var binding: FragmentUserItemBinding
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object{
        const val GITHUB_BUNDLE_TAG = USER_BUNDLE
        fun newInstance(user: GithubUser): MvpAppCompatFragment{
            val bundle = Bundle()
            bundle.putString(GITHUB_BUNDLE_TAG, user.login)
            return UserItemFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        arguments?.let {
            binding.tvLogin.text = it.getString(USER_BUNDLE)
        }
    }

    override fun updateList() {
    }
}