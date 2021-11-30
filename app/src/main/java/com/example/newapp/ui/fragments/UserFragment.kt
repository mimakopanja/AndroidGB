package com.example.newapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.App
import com.example.newapp.BackButtonListener
import com.example.newapp.USER_BUNDLE_TAG
import com.example.newapp.databinding.FragmentUserBinding
import com.example.newapp.mvp.model.api.ApiHolder
import com.example.newapp.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.entity.room.db.Database
import com.example.newapp.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.example.newapp.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.newapp.mvp.presenter.UserPresenter
import com.example.newapp.mvp.view.UserView
import com.example.newapp.ui.navigation.AndroidScreens
import com.example.newapp.ui.adapter.RepositoriesRecyclerViewAdapter
import com.example.newapp.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(USER_BUNDLE_TAG, user)
            }
        }
    }

    private var adapter: RepositoriesRecyclerViewAdapter? = null
    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        val user: GithubUser = arguments?.getParcelable<GithubUser>(USER_BUNDLE_TAG) as GithubUser
        UserPresenter(
            user,
            RetrofitGithubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RoomGithubRepositoriesCache(Database.getInstance())
            ),
            AndroidSchedulers.mainThread(),
            AndroidScreens(),
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }

    override fun init() = with(binding) {
        repoRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRecyclerViewAdapter(presenter.userReposListPresenter)
        repoRecyclerView.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(login: String) {
        binding.userLoginTextView.text = login
    }

    override fun showError(message: Throwable) {
        Log.e("MY_ERROR", message.message?:message.stackTraceToString())
    }

}