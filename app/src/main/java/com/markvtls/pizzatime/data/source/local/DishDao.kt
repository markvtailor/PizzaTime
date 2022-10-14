package com.markvtls.pizzatime.data.source.local

import androidx.room.*

/**
 * DAO for DishesDatabase.
 */
@Dao
interface DishDao {

    @Transaction
    @Query("SELECT * FROM DishEntity WHERE type = :type")
    fun getDishes(type: String): List<DishEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDishes(dishes: List<DishEntity>)

}