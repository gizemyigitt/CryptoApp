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
import com.example.cryptoapp.data.model.User
import com.example.cryptoapp.databinding.FragmentSignUpBinding
import com.example.cryptoapp.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    val TAG:String="SignUpFragment"
    lateinit var binding: FragmentSignUpBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.registerButton.setOnClickListener {
            if (validation()){
                viewModel.register(
                    email = binding.signupEmail.text.toString(),
                    password = binding.signupPassword.text.toString(),
                    user = getUserObj()
                )
            }
        }

        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

        }

    }



    fun getUserObj(): User {
        return User(
            id="",
            name=binding.signupUserNameText.text.toString(),
            email=binding.signupEmail.text.toString()
        )
    }

    fun validation(): Boolean {
        var isValid = true

        if (binding.signupUserNameText.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "Adınızı girin", Toast.LENGTH_LONG).show()
        }
        if (binding.signupEmail.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "E-mailinizi girin", Toast.LENGTH_LONG).show()
        }
        if (binding.signupPassword.text.isNullOrEmpty()){
            isValid = false
            Toast.makeText(binding.root.context, "Şifrenizi girin", Toast.LENGTH_LONG).show()
        }else{
            if (binding.signupPassword.text.toString().length > 8){
                isValid = false
                Toast.makeText(binding.root.context, "Şifre 8 karakterden fazla olamaz", Toast.LENGTH_LONG).show()
            }
        }
        return isValid
    }

    fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.registerButton.setText("")

                }
                is UiState.Failure -> {
                    binding.registerButton.setText("Register")


                }
                is UiState.Success -> {
                    binding.registerButton.setText("Register")


                    findNavController().navigate(R.id.action_signUpFragment_to_home_graph)
                }
            }
        }
    }

}