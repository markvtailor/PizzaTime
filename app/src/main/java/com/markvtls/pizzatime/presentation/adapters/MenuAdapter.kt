package com.markvtls.pizzatime.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.markvtls.pizzatime.R
import com.markvtls.pizzatime.databinding.MenuItemBinding
import com.markvtls.pizzatime.domain.model.Dish

/**
 * RecyclerView Adapter for MenuFragment' dishes list.
 */
class MenuAdapter: ListAdapter<Dish, MenuAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: Dish) {
            binding.apply {
                println(dish.imageUrl)
                pizzaImage.load(dish.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.dish_placeholder)
                    transformations(CircleCropTransformation())
                }

                title.text = dish.name
                description.text = dish.description
                orderButton.text = dish.price
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Dish>() {
            override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}