package com.markvtls.pizzatime.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity for storing PizzaAPI responses.
 */
@Entity
data class DishEntity (
    @PrimaryKey(autoGenerate = false) val name: String,
    val type: String,
    val price: String,
    val description: String,
        )