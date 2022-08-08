package com.example.moneytestapp.presentation.favorite_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneytestapp.MainActivityViewModelFactory
import com.example.moneytestapp.R
import com.example.moneytestapp.domain.CurrencyModel
import com.example.moneytestapp.presentation.CurrencyAdapter
import com.example.moneytestapp.presentation.MainActivityViewModel
import com.example.moneytestapp.presentation.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)

    }

    @Inject
    lateinit var viewModelFactory: FavoriteViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var activityViewModelFactory: MainActivityViewModelFactory
    private val activityViewModel: MainActivityViewModel by activityViewModels { activityViewModelFactory }

    private val adapter =
        CurrencyAdapter(onLikePressed = object : CurrencyAdapter.OnItemClickListener {
            override fun onItemClick(currencyModel: CurrencyModel) {
                viewModel.onUnlikePressed(currencyModel)
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter

        observeViewModel()
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.currencyList.collect {
                adapter.setItems(it)
            }
        }
        lifecycleScope.launch {
            viewModel.currency.collect {
                if (it != null) {
                    adapter.updateItem(it)
                }
            }
        }
        lifecycleScope.launch {
            activityViewModel.currencySorting.collect {
                if (it != null) {
                    adapter.setItems(viewModel.onSortList(it, viewModel.currencyList.value))
                }
            }
        }
    }
}