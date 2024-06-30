package com.nmp.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.uts_anmp.databinding.FragmentUpdateBinding
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.viewmodel.EditViewModel


class UpdateFragment : Fragment(), ButtonDetailClickListener{
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: EditViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater,container,
            false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        val id = UpdateFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetch(id.toInt())
        binding.listener = this
        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.hobbyLD.observe(viewLifecycleOwner, Observer {
            binding.hobby = it
        })
    }

    override fun onButtonDetailClick(v: View) {

    }

    override fun onButtonUpdateClick(v: View) {
        binding.hobby?.let {
            viewModel.update(it)
            Toast.makeText(v.context, "Hobby Updated", Toast.LENGTH_SHORT).show()
            val action = UpdateFragmentDirections.actiontohome()
            Navigation.findNavController(v).navigate(action)
        }
    }
}