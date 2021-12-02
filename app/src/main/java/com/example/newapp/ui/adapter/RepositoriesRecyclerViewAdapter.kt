package com.example.newapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.databinding.ItemRepositoryBinding
import com.example.newapp.mvp.presenter.list.IRepositoryListPresenter
import com.example.newapp.mvp.view.list.RepositoryItemView
import kotlinx.android.extensions.LayoutContainer

class RepositoriesRecyclerViewAdapter(
    val presenter: IRepositoryListPresenter
): RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRepositoryBinding):
            RecyclerView.ViewHolder(binding.root), LayoutContainer, RepositoryItemView{
        override val containerView = binding.root

        override fun setName(text: String?) {
            binding.repositoryName.text = text
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesRecyclerViewAdapter.ViewHolder =
        ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount() = presenter.getCount()
}