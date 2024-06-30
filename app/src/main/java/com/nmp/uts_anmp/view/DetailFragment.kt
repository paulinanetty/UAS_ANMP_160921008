package com.nmp.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nmp.uts_anmp.databinding.FragmentDetailBinding
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.util.loadImage
import com.nmp.uts_anmp.viewmodel.DetailViewModel

class DetailFragment : Fragment(), ButtonDetailClickListener {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var currentPage = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this

        binding.hobby = Hobby("","","","https://cdn.britannica.com/34/235834-050-C5843610/two-different-breeds-of-cats-side-by-side-outdoors-in-the-garden.jpg?w=400&h=300&c=crop")
        val id = arguments?.getString("id")

        if (id == null) {
            Toast.makeText(context, "Invalid ID", Toast.LENGTH_SHORT).show()
            return
        }

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(id.toInt())
        observeViewModel()

        binding.btnNext.setOnClickListener {
            if (currentPage < pageCount - 1) {
                currentPage++
                updatePagenews()
            }
        }

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                updatePagenews()
            }
        }


    }

    fun observeViewModel() {
        detailViewModel.hobbyLD.observe(viewLifecycleOwner, Observer { news->
binding.hobby = news
//            binding.txtTitle.text = detailViewModel.hobbyLD.value?.title
//            binding.txtname.text = detailViewModel.hobbyLD.value?.name
//            binding.txtnews.text = detailViewModel.hobbyLD.value?.description
//            binding.imageView2.loadImage(detailViewModel.hobbyLD.value?.photoUrl, binding.progressBar2)

            val content = news.description
            if (content != null) {
                pageCount = (content.length + PAGE_SIZE - 1) / PAGE_SIZE
            }
            updatePagenews()
        })

    }
    private fun updatePagenews() {
        detailViewModel.hobbyLD.value?.let { news ->
            val content = news.description
            val startIndex = currentPage * PAGE_SIZE
            val endIndex = content?.let { minOf(startIndex + PAGE_SIZE, it.length) }
            val pageContent = endIndex?.let { content?.substring(startIndex, it) }
            binding.txtnews.text = pageContent
        }
    }

    companion object {
        private const val PAGE_SIZE = 500 // Sesuaikan sesuai kebutuhan
        private var pageCount = 0
    }

    override fun onButtonDetailClick(v: View) {
        TODO("Not yet implemented")
    }

    override fun onButtonUpdateClick(v: View) {
        TODO("Not yet implemented")
    }

}