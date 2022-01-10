package com.example.danytransfernow.feature.transfer.presentation

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.danytransfernow.databinding.TransferFragmentBinding
import com.example.danytransfernow.feature.login.domain.usecase.TransferReqParam
import com.example.danytransfernow.feature.transfer.domain.model.Payee
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransferFragment : Fragment() {

    companion object {
        fun newInstance() = TransferFragment()
    }

    private var _binding: TransferFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: TransferViewModel by viewModels()
    private var payList: List<Payee> = listOf()
    private var selectedPay: Payee? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransferFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.payees.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (!it.data.isNullOrEmpty()) {
                    payList = it.data
                    setUpSpiner(it.data.map { it.name })
                }
            }
        })
        viewModel.isTransfered.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.progress.visibility = View.GONE
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        "Wow!! Transfer sSuccessful",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Transfer Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getPayees()
        binding.buttonTransfer.setOnClickListener {
            if (selectedPay != null && !binding.amountEditText.text.isNullOrEmpty()) {
                binding.progress.visibility = View.VISIBLE
                viewModel.transfer(
                    TransferReqParam(
                        amount = binding.amountEditText.text.toString().toFloat(),
                        description = binding.disprition.text.toString(),
                        receipientAccountNo = selectedPay!!.accountNo
                    )
                )
            }

        }

    }


    fun setUpSpiner(list: List<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, list)
        binding.spinnerPayee.adapter = arrayAdapter
        binding.spinnerPayee.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedPay = payList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // sometimes you need nothing here
            }
        }

    }
}