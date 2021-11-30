package com.example.newapp.mvp.presenter

import com.example.newapp.BackButtonListener
import com.example.newapp.mvp.model.entity.GithubRepository
import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.repo.IGithubUsersRepo
import com.example.newapp.mvp.presenter.list.IRepositoryListPresenter
import com.example.newapp.mvp.view.UserView
import com.example.newapp.mvp.view.list.RepositoryItemView
import com.example.newapp.mvp.model.navigation.IScreens
import com.example.newapp.mvp.model.repo.IGithubRepositoriesRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    private val user: GithubUser,
    private val repo: IGithubRepositoriesRepo,
    private val uiScheduler: Scheduler,
    private val screens: IScreens,
    private val router: Router
): MvpPresenter<UserView>(), BackButtonListener {

    val userReposListPresenter = UserReposListPresenter()

    class UserReposListPresenter : IRepositoryListPresenter {
        val repos = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepositoryItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setName(it) }
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user.login?.let { viewState.setLogin(it) }
        loadData()

        userReposListPresenter.itemClickListener = { itemView ->
            val repo = userReposListPresenter.repos[itemView.pos]
            router.navigateTo(screens.repository(repo))
        }
    }

    private fun loadData() {
        repo.getUserRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}