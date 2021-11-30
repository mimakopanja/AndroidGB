package com.example.newapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.App
import com.example.newapp.BackButtonListener
import com.example.newapp.databinding.FragmentUsersBinding
import com.example.newapp.mvp.model.api.ApiHolder
import com.example.newapp.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.newapp.mvp.model.cache.room.RoomGithubUsersCache
import com.example.newapp.mvp.model.entity.room.db.Database
import com.example.newapp.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.newapp.mvp.presenter.UsersPresenter
import com.example.newapp.mvp.view.UsersView
import com.example.newapp.ui.navigation.AndroidScreens
import com.example.newapp.ui.adapter.UsersRecyclerViewAdapter
import com.example.newapp.ui.image.GlideImageLoader
import com.example.newapp.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            RetrofitGithubUsersRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RoomGithubUsersCache(Database.getInstance())
            ),
            App.instance.router,
            AndroidSchedulers.mainThread(),
            AndroidScreens()
        )
    }
    private var adapter: UsersRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.usersRecyclerView.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(message: Throwable) {
        Log.e("MY_ERROR", message.message?:message.stackTraceToString())
    }

    override fun backPressed() = presenter.backPressed()
}