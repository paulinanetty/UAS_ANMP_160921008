package com.nmp.uts_anmp.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.databinding.FragmentHomeBinding
import com.nmp.uts_anmp.databinding.FragmentRegisterBinding
import com.nmp.uts_anmp.viewmodel.DetailViewModel
import com.nmp.uts_anmp.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {
    private lateinit var bind: FragmentRegisterBinding
    private lateinit var regisViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentRegisterBinding.inflate(inflater, container, false)
        return bind.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.btnRegister.setOnClickListener {
            regisViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
            val fname = bind.txtFname.text.toString()
            val lname = bind.txtLname.text.toString()
            val username = bind.txtUsername.text.toString()
            val email = bind.txtEmail.text.toString()
            val pass = bind.txtPass.text.toString()
            val Cpass = bind.txtCpass.text.toString()
            if (fname.isNotEmpty() && lname.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()&& Cpass.isNotEmpty()) {
                if (pass == Cpass) {
                    regisViewModel.addUser(username,fname, lname, email, pass)
                    val action = RegisterFragmentDirections.actionloginFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(context, "Pasword Is Different!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Data Is Empty!", Toast.LENGTH_SHORT).show()
            }

        }
        bind.txtLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionloginFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}