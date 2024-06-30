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
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.databinding.FragmentProfileBinding
import com.nmp.uts_anmp.model.User
import com.nmp.uts_anmp.viewmodel.LoginViewModel

class ProfileFragment : Fragment(), UserLoginClick {

    private lateinit var bind:FragmentProfileBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentProfileBinding.inflate(inflater, container, false)
        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("id", "")
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if (id != null) {
            loginViewModel.fetch(id)
        }
        bind.listener = this

        observeViewModel()
    }


    fun observeViewModel() {
        loginViewModel.userLD.observe(viewLifecycleOwner, Observer {
            bind.user = it
        })
    }

    override fun onUserLoginClick(v: View) {
        TODO("Not yet implemented")
    }

    
    override fun onUserUpdateClick(v: View) {
        bind.user?.let {
            it.fname = bind.txtfname.text.toString()
            it.lname = bind.txtlname.text.toString()
            it.password = bind.txtpass.text.toString()

            loginViewModel.update(it)
            Toast.makeText(v.context, "User Updated", Toast.LENGTH_SHORT).show()
            val action = UpdateFragmentDirections.actiontohome()
            Navigation.findNavController(v).navigate(action)
        }
    }

    override fun onUserLogoutClick(v: View) {
        loginViewModel.logout()
        val action = ProfileFragmentDirections.actiontologinFragment()
        Navigation.findNavController(v).navigate(action)
    }

}