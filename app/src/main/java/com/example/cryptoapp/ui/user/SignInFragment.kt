package com.example.cryptoapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentSignInBinding
import com.example.cryptoapp.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    val TAG: String = "LoginFragment"
    lateinit var binding: FragmentSignInBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.signinButton.setOnClickListener {
            if (validation()) {
                viewModel.login(
                    email = binding.signinEmail.text.toString(),
                    password = binding.signinPassword.text.toString()
                )
            }
        }

        binding.forgetpassText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_resetPasswordFragment)
        }

        binding.sinupText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    fun observer(){
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.signinButton.setText("")

                }
                is UiState.Failure -> {
                    binding.signinButton.setText("Login")

                }
                is UiState.Success -> {
                    binding.signinButton.setText("Login")

                    findNavController().navigate(R.id.action_signInFragment_to_home_graph)
                }
            }
        }
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.signinEmail.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "E-mailinizi girin", Toast.LENGTH_LONG).show()
        }

        if (binding.signinPassword.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "Şifrenizi girin", Toast.LENGTH_LONG).show()
        }else{
            if (binding.signinPassword.text.toString().length > 8){
                isValid = false
                Toast.makeText(binding.root.context, "Şifre 8 karakterden fazla olamaz", Toast.LENGTH_LONG).show()
            }
        }
        return isValid
    }

    /*override fun onStart() {
        super.onStart()
        viewModel.getSession { user ->
            if (user != null){
                findNavController().navigate(R.id.action_signInFragment_to_home_graph)
            }
        }
    }*/

}