package com.example.moneytestapp.presentation.popular_screen

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

class PopularFragment : Fragment() {

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)

    }


    @Inject
    lateinit var viewModelFactory: PopularViewModelFactory
    private val viewModel: PopularViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var activityViewModelFactory: MainActivityViewModelFactory
    private val activityViewModel: MainActivityViewModel by activityViewModels { activityViewModelFactory }

    private val adapter =
        CurrencyAdapter(onLikePressed = object : CurrencyAdapter.OnItemClickListener {
            override fun onItemClick(currencyModel: CurrencyModel) {
                if (currencyModel.isLike) {
                    viewModel.onUnlikePressed(currencyModel)
                } else viewModel.onLikePressed(currencyModel)
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
            activityViewModel.currency.collect {
                if (it != null) {
                    viewModel.loadLatestCurrency(it)
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
