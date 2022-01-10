package com.example.danytransfernow.feature.transfer.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.danytransfernow.MainApplication
import com.example.danytransfernow.R
import com.example.danytransfernow.databinding.DashboardFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private var _binding: DashboardFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardView.setBackgroundResource(R.drawable.card_bg)
        binding.buttonTransfer.setBackgroundResource(R.drawable.button_border)
        binding.buttonTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transferFragment)
        }

        binding.rcTransations.layoutManager = LinearLayoutManager(activity)
        // This will pass the ArrayList to our Adapter
        val adapter = TransactionsAdapter()
        // Setting the Adapter with the recyclerview
        binding.rcTransations.adapter = adapter

        viewModel.getBalance()
        viewModel.getTransactionList()
        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
        viewModel.transactionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setList(it.data)
                Log.d("dd", " trsnn$it")
            }
        })
        viewModel.balance.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textViewBalance.text = "SGD${it.balance}"
                binding.textViewAccountNo.text = it.balance.toString()
                binding.textViewAcHolder.text = MainApplication.user?.username.orEmpty()

            }
        })
        binding.logout.setOnClickListener {
            viewModel.getBalance()
            viewModel.getTransactionList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun hidShowProgress(isShow: Boolean) {
//        if (isShow) {
//            binding.progress.visibility = View.VISIBLE
//        } else {
//            binding.progress.visibility = View.GONE
//        }
//
//    }
}