package com.example.moneytestapp.presentation.popular_screen

//import com.example.moneytestapp.work.NotifyWorkerFactory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytestapp.MainActivityViewModelFactory
import com.example.moneytestapp.data.UiState
import com.example.moneytestapp.databinding.FragmentPopularBinding
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

//    @Inject
//    lateinit var notifyWorkerFactory: NotifyWorkerFactory
//    @Inject
//    lateinit var notifyWorker: NotifyWorker


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

    private var binding: FragmentPopularBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireContext().appComponent.inject(this)


//        * WorkRequest request = new OneTimeWorkRequest.Builder(FooWorker.class).build();
//        * workManager.enqueue(request);
//        * LiveData<WorkInfo> status = workManager.getWorkInfoByIdLiveData(request.getId());
//        * status.observe(...);


//        val asdas = WorkManager.getInstance(requireContext()).enqueue(
//            PeriodicWorkRequestBuilder<NotifyWorker>(
//                30,
//                TimeUnit.MINUTES,
//                25,
//                TimeUnit.MINUTES
//            ).build()
//        )
//        val myWorkRequest = PeriodicWorkRequestBuilder<NotifyWorker>(30,
//                TimeUnit.MINUTES,
//                25,
//                TimeUnit.MINUTES).build()
//
//        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//        WorkManager.initialize(requireContext(), Configuration.Builder).enqueue(myWorkRequest)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }

//        val workManager = WorkManager.getInstance(requireContext())
//        val request = OneTimeWorkRequest.Builder(NotifyWorker::class.java).build()
//        workManager.enqueue(request)

//        val request = PeriodicWorkRequestBuilder<NotifyWorker>(
//            10,
//            TimeUnit.MINUTES,
//            2,
//            TimeUnit.MINUTES
//        ).build()
//        WorkManager.getInstance(requireContext()).enqueue(request)
//
//        WorkManager.getInstance(requireContext())
//            .getWorkInfoByIdLiveData(request.id)
//            .observe(viewLifecycleOwner, Observer { workInfo ->
//                Log.d("asdasd", workInfo.toString())
//                if ((workInfo != null) &&
//                    (workInfo.state == WorkInfo.State.ENQUEEDED)) {
//                    val myOutputData = workInfo.outputData.getString(KEY_MY_DATA)
//                }
//            })

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
                when (it) {
                    is UiState.Loading -> binding?.progressBar?.visibility = ProgressBar.VISIBLE
                    is UiState.Success -> {
                        viewModel.loadLatestCurrency(it.result)
                        binding?.progressBar?.visibility = ProgressBar.INVISIBLE
                    }
                    is UiState.Error -> {}
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
