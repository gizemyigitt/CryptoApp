package com.example.cryptoapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cryptoapp.databinding.FragmentResetPasswordBinding
import com.example.cryptoapp.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    val TAG: String = "ResetPasswordFragment"
    lateinit var binding: FragmentResetPasswordBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.sendButton.setOnClickListener {
            if (validation()){
                viewModel.forgotPassword(binding.resendEmailText.text.toString())
            }
        }
    }

    private fun observer(){
        viewModel.forgotPassword.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.sendButton.setText("")

                }
                is UiState.Failure -> {
                    binding.sendButton.setText("Send")

                }
                is UiState.Success -> {
                    binding.sendButton.setText("Send")

                }
            }
        }
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.resendEmailText.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "E-mailinizi girin", Toast.LENGTH_LONG).show()
        }

        return isValid
    }


}