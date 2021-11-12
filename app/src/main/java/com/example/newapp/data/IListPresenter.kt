package com.example.newapp.data

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
            fun bindView(view: V)
            fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<IUserItemView>