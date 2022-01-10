package com.example.danytransfernow.feature.login.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.danytransfernow.R
import com.example.danytransfernow.databinding.FragmentRegisterBinding
import com.example.danytransfernow.feature.login.domain.usecase.LoginParams
import com.example.danytransfernow.feature.transfer.presentation.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val viewModel: RegistrationViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            it?.let {
                hidShowProgress(false)
                if (it)
                    startActivity(Intent(activity, DashboardActivity::class.java))
            }
        })
        binding.buttonRegister.setOnClickListener {
            validateAndRegister()
        }
    }

    private fun validateAndRegister() {
        var isValid = true
        if (binding.usernameEditText.text.toString().isEmpty()) {
            binding.usernameInputText.error = "UserName is required"
            isValid = false
        } else {
            binding.usernameInputText.error = null
        }

        if (binding.passEditText.text.toString().isEmpty()) {
            binding.passwordInputText.error = "Password is required"
            isValid = false
        } else {
            binding.passwordInputText.error = null

        }

        if (binding.confirmPasswordEditText.text.toString().isEmpty()) {
            binding.confirmPasswordInputText.error = "Confirm Password "
            isValid = false
        } else {
            binding.confirmPasswordInputText.error = null

        }
        if (binding.confirmPasswordEditText.text.toString() != binding.passEditText.text.toString())
            isValid = false

        if (isValid) {
            hidShowProgress(true)
            viewModel.register(
                LoginParams(
                    binding.usernameEditText.text.toString(),
                    binding.passEditText.text.toString()
                )
            )
        }

    }

    private fun hidShowProgress(isShow: Boolean) {
        if (isShow) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}