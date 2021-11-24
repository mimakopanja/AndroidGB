package com.example.newapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.databinding.ItemUserBinding
import com.example.newapp.mvp.model.image.IImageLoader
import com.example.newapp.mvp.presenter.list.IUserListPresenter
import com.example.newapp.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer

class UsersRecyclerViewAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
):
RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemUserBinding):
            RecyclerView.ViewHolder(binding.root), LayoutContainer, UserItemView{
        override fun setLogin(text: String?) {
            binding.usernameTextView.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, binding.avatarImage)
        }

        override var pos = -1
        override val containerView = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount()= presenter.getCount()
}