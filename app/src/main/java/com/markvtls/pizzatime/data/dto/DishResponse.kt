package com.markvtls.pizzatime.data.dto

/**
 * This class represents response from PizzaAPI.
 */
data class DishResponse(
    var name: String,
    val price: String,
    var description: String
        )