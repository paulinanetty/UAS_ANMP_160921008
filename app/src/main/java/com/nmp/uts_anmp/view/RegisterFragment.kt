package com.nmp.uts_anmp.view

import com.nmp.uts_anmp.model.User
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
        regisViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        bind.btnRegister.setOnClickListener {
            var user = User(
                bind.txtFname.text.toString(),
                bind.txtLname.text.toString(),
                bind.txtUsername.text.toString(),
                bind.txtPass.text.toString(),
                bind.txtEmail.text.toString(),
            )
            val Cpass = bind.txtCpass.text.toString()
            val list = listOf(user)
            if (user.fname!!.isNotEmpty() && user.lname!!.isNotEmpty() && user.username!!.isNotEmpty() && user.email!!.isNotEmpty() && user.password!!.isNotEmpty() && Cpass.isNotEmpty()) {
                if (user.password == Cpass) {
                    regisViewModel.addUser(list)
                    Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(it).popBackStack()

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