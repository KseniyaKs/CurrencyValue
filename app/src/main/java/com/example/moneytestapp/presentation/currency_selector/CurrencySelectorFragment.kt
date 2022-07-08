package com.example.moneytestapp.presentation.currency_selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moneytestapp.R
import com.example.moneytestapp.domain.CurrencyFullName
import com.example.moneytestapp.presentation.MainActivityViewModel
import com.example.moneytestapp.presentation.currency_selector.sorting_screen.SortingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class CurrencySelectorFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencySelectorFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_selector, container, false)
    }

    private val viewModel: CurrencySelectorViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()


    private val adapter by lazy {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mutableListOf<String>())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val sort = view.findViewById<Button>(R.id.sort_btn)

        sort.setOnClickListener {
                SortingFragment.newInstance().show(childFragmentManager, SortingFragment::javaClass.name)
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.prompt = resources.getString(R.string.currency_selection)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val currency = viewModel.currencyList.value[position]
                 activityViewModel.setSelectedCurrency(currency.code)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        viewModel
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.currencyList.collect { list ->
                adapter.addAll( list.map { it.code + " " + it.fullName })
                adapter.notifyDataSetChanged()
            }
        }
    }
}
