package com.example.newapp.ui.fragments

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.newapp.App
import com.example.newapp.BackButtonListener
import com.example.newapp.REPOSITORY_BUNDLE_TAG
import com.example.newapp.databinding.FragmentRepositoryBinding
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.presenter.RepositoryPresenter
import com.example.newapp.mvp.view.RepositoryView
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment: MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private lateinit var binding: FragmentRepositoryBinding

    private val presenter by moxyPresenter {
        val repository: GithubRepository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_BUNDLE_TAG) as GithubRepository
        RepositoryPresenter(repository, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
       android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }

    companion object{
        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(REPOSITORY_BUNDLE_TAG, repository)
            }
        }
    }

    override fun fillData(data: GithubRepository) {
        binding.apply {
            repoName.text = data.name
            repoDescription.text = data.description
            repoCreated.text = data.createdAt
            repoLanguage.text = data.language
            repoForksCount.text = data.forks.toString()
        }
    }
}