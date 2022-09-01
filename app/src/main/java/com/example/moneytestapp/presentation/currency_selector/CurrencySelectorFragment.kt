package com.example.moneytestapp.presentation.currency_selector

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moneytestapp.MainActivityViewModelFactory
import com.example.moneytestapp.R
import com.example.moneytestapp.databinding.FragmentCurrencySelectorBinding
import com.example.moneytestapp.presentation.MainActivityViewModel
import com.example.moneytestapp.presentation.appComponent
import com.example.moneytestapp.presentation.sorting_screen.SortingFragment
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencySelectorFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencySelectorFragment()
    }

    @Inject
    lateinit var viewModelFactory: CurrencySelectorViewModelFactory
    private val viewModel: CurrencySelectorViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var activityViewModelFactory: MainActivityViewModelFactory
    private val activityViewModel: MainActivityViewModel by activityViewModels { activityViewModelFactory }

    private val adapter by lazy {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf<String>()
        )
    }

    private var binding: FragmentCurrencySelectorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencySelectorBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.sortBtn?.setOnClickListener {
            SortingFragment.newInstance()
                .show(childFragmentManager, SortingFragment::javaClass.name)
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding?.apply {
            spinner.adapter = adapter

            spinner.prompt = resources.getString(R.string.currency_selection)

            val qweqwe = viewModel.currencyList.value
            Log.d("qwer23", qweqwe.size.toString())


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

                override fun onNothingSelected(parent: AdapterView<*>?) {}
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
                adapter.addAll(list.map { it.code + " " + it.fullName })
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
