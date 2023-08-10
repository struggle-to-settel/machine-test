package com.test.machinetest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsalltime.databinding.ItemNewsBinding
import com.test.machinetest.model.Article

class NewsAdapter(private val handleClick: (Int) -> Unit) : BaseAdapter<Article?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItemAt(position)?.let { item ->
            (holder as NewViewHolder).apply {
                binding.tvTitle.text = item.title?:""
                binding.tvDescription.text = item.description?:item.content?:""
                if (item.urlToImage.isNullOrEmpty()) binding.ivImage.visibility = View.GONE
                else {
                    binding.ivImage.visibility = View.VISIBLE
                    Glide.with(binding.ivImage).load(item.urlToImage).transform(RoundedCorners(12))
                        .into(binding.ivImage)
                }
            }
        }
    }


    inner class NewViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivDelete.setOnClickListener {
                handleClick.invoke(adapterPosition)
            }
        }
    }
}