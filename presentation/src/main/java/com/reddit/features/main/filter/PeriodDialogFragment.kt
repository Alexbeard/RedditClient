package com.reddit.features.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.reddit.R
import com.reddit.common.BaseBottomSheetDialog
import com.reddit.databinding.DialogPeriodBinding
import com.reddit.domain.type.Period
import javax.inject.Inject

class PeriodDialogFragment : BaseBottomSheetDialog() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PeriodViewModel
    private lateinit var binding: DialogPeriodBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_period, null, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PeriodViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.filter.observe(this, Observer {
            when (it) {
                Period.Hour -> binding.hour.isChecked = true
                Period.Day -> binding.day.isChecked = true
                Period.Week -> binding.week.isChecked = true
                Period.Month -> binding.month.isChecked = true
                Period.Year -> binding.year.isChecked = true
                Period.All -> binding.all.isChecked = true
            }
        })
        viewModel.closeEvent.observe(this, Observer {
            dismiss()
        })
    }

    companion object {
        fun run(fragmentManager: FragmentManager) {
            PeriodDialogFragment().show(fragmentManager, "Periods")
        }
    }
}