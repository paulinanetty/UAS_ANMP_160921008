package com.nmp.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.databinding.FragmentCreateNewsBinding
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.viewmodel.EditViewModel


class CreateNewsFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewsBinding
    private lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(EditViewModel::class.java)
        binding.btnaddNews.setOnClickListener {
            var news = Hobby(binding.txtName.text.toString(),
                binding.txtTitle.text.toString(), binding.txtDesc.text.toString(),binding.txtPhoto.text.toString())
            val list = listOf(news)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}