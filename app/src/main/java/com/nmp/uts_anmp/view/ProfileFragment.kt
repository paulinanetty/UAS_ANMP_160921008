package com.nmp.uts_anmp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.databinding.FragmentProfileBinding
import com.nmp.uts_anmp.databinding.FragmentRegisterBinding
import com.nmp.uts_anmp.viewmodel.EditViewModel
import com.nmp.uts_anmp.viewmodel.RegisterViewModel

class ProfileFragment : Fragment() {

    private lateinit var bind:FragmentProfileBinding
    private lateinit var editViewModel: EditViewModel
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
        val username = sharedPreferences.getString("username", "")
        bind.btnedit.setOnClickListener {
            editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)
            val fname = bind.txtfname.text.toString()
            val lname = bind.txtlname.text.toString()
            val pass = bind.txtpass.text.toString()
            val Cpass = bind.txtcpass.text.toString()
            if (fname.isNotEmpty() && lname.isNotEmpty() && pass.isNotEmpty() && Cpass.isNotEmpty()) {
                if (pass == Cpass) {
                    editViewModel.updateUser(fname, lname, pass, username.toString())
                } else {
                    Toast.makeText(context, "Pasword Is Different!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Data Is Empty!", Toast.LENGTH_SHORT).show()
            }

        }
        bind.btnlogout.setOnClickListener{
            logout()
            val action = ProfileFragmentDirections.actiontologinFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.remove("username")
        editor.apply()
    }

}