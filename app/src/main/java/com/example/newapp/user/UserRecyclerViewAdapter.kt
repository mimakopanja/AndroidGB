package com.example.newapp.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.data.IUserListPresenter
import com.example.newapp.data.IUserItemView
import com.example.newapp.databinding.ItemUserBinding

class UserRecyclerViewAdapter(private val presenter: IUserListPresenter):
RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemUserBinding):
            RecyclerView.ViewHolder(binding.root), IUserItemView{
        override var pos = -1

        override fun setLogin(text: String) = with(binding) {
            tvLogin.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(ItemUserBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)). apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount()= presenter.getCount()
}