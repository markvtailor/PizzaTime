package com.markvtls.pizzatime.domain.model

import com.markvtls.pizzatime.data.source.local.DishEntity
import com.markvtls.pizzatime.utils.Constants.DESSERTS_PICS
import com.markvtls.pizzatime.utils.Constants.PIZZA_PICS

/**
 * Data class for preparing database entities for UI.
 */
data class Dish (
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String
        )

/**
 * Mapping database entity to domain model.
 */
fun DishEntity.toDomain(): Dish {
    val imageUrl = when(this.type) {
        "pizza" -> PIZZA_PICS.random()
        "desserts" -> DESSERTS_PICS.random()
        else -> ""
    }
    return Dish(
        this.name,
        this.description,
        "От ${this.price} р",
        imageUrl
    )
}