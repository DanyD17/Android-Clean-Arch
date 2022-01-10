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
import com.example.danytransfernow.databinding.FragmentLoginBinding
import com.example.danytransfernow.feature.login.domain.usecase.LoginParams
import com.example.danytransfernow.feature.transfer.presentation.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setBackgroundResource(R.drawable.button_border)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        viewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            it?.let {
                hidShowProgress(false)
                if (it)
                    startActivity(Intent(activity, DashboardActivity::class.java))
            }
        })
        binding.buttonLogin.setOnClickListener {
            hidShowProgress(true)
            validateAndLogin()
        }
    }

    private fun validateAndLogin() {
        if (binding.usernameEditText.text.toString().isEmpty())
            binding.loginUsernameEditText.error = "UserName is required"
        else binding.loginPasswordEditText.error = null

        if (binding.passwordEditText.text.toString().isEmpty())
            binding.loginPasswordEditText.error = "Password is required"
        else binding.loginPasswordEditText.error = null

        viewModel.login(
            LoginParams(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hidShowProgress(isShow: Boolean) {
        if (isShow) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }

    }
}