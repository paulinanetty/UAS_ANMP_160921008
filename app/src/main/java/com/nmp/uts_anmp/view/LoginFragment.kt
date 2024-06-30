package com.nmp.uts_anmp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.uts_anmp.databinding.FragmentLoginBinding
import com.nmp.uts_anmp.model.User
import com.nmp.uts_anmp.viewmodel.LoginViewModel


class LoginFragment : Fragment(), UserLoginClick {

    private lateinit var bind: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        return bind.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        bind.listener = this
        observeViewModel()
//
//        bind.btnLogin.setOnClickListener {
//
//            val username = bind.txtUsername3.text.toString()
//            val password = bind.txtPassword3.text.toString()
//
//            if ( username.isNotEmpty() && password.isNotEmpty()) {
//                sharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
//                loginSuccess(username)
//                loginViewModel.login(username, password)
//
//            } else {
//                Toast.makeText(context, "Data Is Empty!", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//        loginViewModel.userLD.observe(viewLifecycleOwner, Observer { login ->
//            if (login) {
//
//                val action = LoginFragmentDirections.actionhomeFragment()
//                Navigation.findNavController(requireView()).navigate(action)
//            }
//        })
        bind.txtRegister2.setOnClickListener {

            val action = LoginFragmentDirections.actionregisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

//        fun saveAuthToken(token: String) {
//            val editor = sharedPreferences.edit()
//            editor.putString("auth_token", token)
//            editor.apply()
//        }

    }
    fun observeViewModel() {
        loginViewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

                val action = LoginFragmentDirections.actionhomeFragment()
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(context, "Password and Username is Wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onUserLoginClick(v: View) {
        val username = bind.txtUsername3.text.toString()
        val password = bind.txtPassword3.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            loginViewModel.login(username, password)
            sharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
            loginSuccess(v.tag.toString())
        } else {
            Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loginSuccess(id: String) {
        // Simpan username ke SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.apply()

    }

    override fun onUserUpdateClick(v: View) {
        TODO("Not yet implemented")
    }

    override fun onUserLogoutClick(v: View) {
        TODO("Not yet implemented")
    }


}