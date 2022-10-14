package com.markvtls.pizzatime.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markvtls.pizzatime.domain.model.Dish
import com.markvtls.pizzatime.domain.use_cases.GetDessertsUseCase
import com.markvtls.pizzatime.domain.use_cases.GetPizzasUseCase
import com.markvtls.pizzatime.domain.use_cases.GetSavedDishesByTypeUseCase
import com.markvtls.pizzatime.domain.use_cases.InsertDishesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * MenuFragment' ViewModel.
 */
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getPizzas: GetPizzasUseCase,
    private val getDesserts: GetDessertsUseCase,
    private val saveDishes: InsertDishesUseCase,
    private val getSavedByType: GetSavedDishesByTypeUseCase

): ViewModel() {

    /** Dishes list. Flow is empty by default */
    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes get() = _dishes


    /** Utilizing usecases to make a request to API and save response */
    fun getDishes(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val dishRequest = when(type) {
                    "pizza" -> getPizzas()
                    "desserts" -> getDesserts()
                    else -> throw Exception()
                }

                dishRequest.collect { response ->
                    saveDishes(type, response)
                    getSavedDishes(type)
                }

        } catch (e: UnknownHostException) {
            /** If request failed, try to load local data */
            Log.e("Error","Internet connection is missing")
            getSavedDishes(type)
        } catch (e: Exception) {
            Log.e("Error","Unexpected exception!")
                getSavedDishes(type)
        }
    } }

    /** Utilizing usecases to get dishes from local database */
    fun getSavedDishes(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedByType(type).collect { savedDishes ->
                _dishes.emit(savedDishes)
            }
        }
    }
}