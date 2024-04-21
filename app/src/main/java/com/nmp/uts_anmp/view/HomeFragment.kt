package com.nmp.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp.uts_anmp.databinding.FragmentHomeBinding
import com.nmp.uts_anmp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private val hobbyListAdapter  = AppListAdapter(arrayListOf())
    private lateinit var bind: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.refresh()

        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        bind.recyclerView.adapter = hobbyListAdapter

        observeViewModel()

        bind.refreshLayout.setOnRefreshListener {
            bind.recyclerView.visibility = View.GONE
            bind.txterror.visibility = View.GONE
            bind.progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            bind.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.hobbyLD.observe(viewLifecycleOwner, Observer {
            hobbyListAdapter.updateStudentList(it)
        })


        viewModel.hobbyLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                bind.txterror?.visibility = View.VISIBLE
            } else {
                bind.txterror?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                bind.recyclerView.visibility = View.GONE
                bind.progressBar.visibility = View.VISIBLE
            } else {
                bind.recyclerView.visibility = View.VISIBLE
                bind.progressBar.visibility = View.GONE
            }
        })



    }


}