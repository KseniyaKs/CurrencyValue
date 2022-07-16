package com.example.moneytestapp.presentation.sorting_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.moneytestapp.R
import com.example.moneytestapp.presentation.MainActivityViewModel


class SortingFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_sorting, container, false)
    }

    companion object {
        fun newInstance() = SortingFragment()
    }

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.filter_dialog_size)
        val height = resources.getDimensionPixelSize(R.dimen.filter_dialog_size)
        dialog?.window?.setLayout(width, height)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sort = view.findViewById<RadioGroup>(R.id.sort)

        sort.setOnCheckedChangeListener { group, checkedId ->
            activityViewModel.setSortCurrency(onRadioButtonClicked(checkedId))
            this.dialog?.dismiss()
        }
    }


    fun onRadioButtonClicked(viewId: Int): SortingModel {
        return when (viewId) {
            R.id.ascendingName -> SortingModel(
                type = SortingType.ALPHABET,
                parameter = SortingParameter.ASCENDING
            )
            R.id.descendingName -> SortingModel(
                type = SortingType.ALPHABET,
                parameter = SortingParameter.DESCENDING
            )
            R.id.ascendingValue -> SortingModel(
                type = SortingType.VALUE,
                parameter = SortingParameter.ASCENDING
            )

            R.id.descendingValue -> SortingModel(
                type = SortingType.VALUE,
                parameter = SortingParameter.DESCENDING
            )
            else -> {
                SortingModel(
                    type = SortingType.ALPHABET,
                    parameter = SortingParameter.ASCENDING
                )
            }
        }
    }
}