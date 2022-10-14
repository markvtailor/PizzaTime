package com.markvtls.pizzatime.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.markvtls.pizzatime.R
import com.markvtls.pizzatime.databinding.FragmentMenuBinding
import com.markvtls.pizzatime.domain.model.Dish
import com.markvtls.pizzatime.presentation.MenuViewModel
import com.markvtls.pizzatime.presentation.adapters.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * This fragment is used to show dishes menu.
 */
@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Getting pizza by default. */
        viewModel.getSavedDishes("pizza")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Marking Pizza category as default. */
        markCategory(binding.pizzaCategoryButton)

        /** Implementing category button functionality. */
        binding.categoryButtonsList.forEach { categoryButton ->
            categoryButton.setOnClickListener {
                markCategory(it as MaterialButton)
                chooseCategory(it.hint.toString())
            }
        }

        /** Observing dishes list and filling RecyclerView. */
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.dishes.collect { dishes ->
                loadMenu(dishes)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /** Initializing RecyclerView. */
    private fun loadMenu(dishes: List<Dish>) {
        val menuList = binding.menuList
        menuList.layoutManager = LinearLayoutManager(this.context)
        val adapter = MenuAdapter()
        menuList.adapter = adapter
        adapter.submitList(dishes)
    }

    /** Requesting dishes by type. */
    private fun chooseCategory(type: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getDishes(type)
        }
    }

    /** Highlighting current category. */
    private fun markCategory(categoryButton: MaterialButton) {

        /** Set all the category buttons to default */
        binding.categoryButtonsList.forEach {
            it as MaterialButton
            it.apply {
                setTextColor(resources.getColor(R.color.gray))
                setBackgroundColor(resources.getColor(R.color.white))
            }

        }

        /** Set chosen category colors. */
        categoryButton.apply {
            setTextColor(resources.getColor(R.color.pink))
            setBackgroundColor(resources.getColor(R.color.light_pink))
        }

    }
}