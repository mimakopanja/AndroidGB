package com.example.newapp.mvp.presenter


import com.example.newapp.mvp.model.entity.GithubUser
import com.example.newapp.mvp.model.repo.IGithubUsersRepo
import com.example.newapp.mvp.presenter.list.IUserListPresenter
import com.example.newapp.mvp.view.UsersView
import com.example.newapp.mvp.view.list.UserItemView
import com.example.newapp.mvp.model.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val  usersRepo: IGithubUsersRepo,
    val router: Router,
    val uiScheduler: Scheduler,
    val screens: IScreens
): MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    var loadUsersDisposable: Disposable? = null

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadImage(it) }
        }
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        loadUsersDisposable = usersRepo.loadUsers()
            .observeOn(uiScheduler)
            .subscribe(
                { users ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                }, { error -> viewState.showError(error)}
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        loadUsersDisposable?.dispose()
    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }
}