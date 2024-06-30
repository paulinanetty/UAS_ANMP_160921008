package com.nmp.uts_anmp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nmp.uts_anmp.databinding.AppListItemBinding
import com.nmp.uts_anmp.model.Hobby
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class AppListAdapter (val hobbyList:ArrayList<Hobby>)
    : RecyclerView.Adapter<AppListAdapter.HobbyViewHolder>(),ButtonDetailClickListener {
    class HobbyViewHolder(var bind: AppListItemBinding)
        : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = AppListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HobbyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return hobbyList.size
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.bind.hobby = hobbyList[position]
        holder.bind.listener = this
//        holder.bind.txttitle.text = hobbyList[position].title
//        holder.bind.txtName.text = hobbyList[position].name
//        holder.bind.txtDesc.text = hobbyList[position].description
//        val i = hobbyList[position].id.toString()
//        holder.bind.btnRead.setOnClickListener {
//            val action = HomeFragmentDirections.actiondetailFragment2(id=i)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.bind.btnUpdate.setOnClickListener {
//            val action = HomeFragmentDirections.actiontoupdate(id=i)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//        picasso.build().load(hobbyList[position].photoUrl)
//            .into(holder.bind.imageView, object: Callback {
//                override fun onSuccess() {
//                    holder.bind.progressBar2.visibility = View.INVISIBLE
//                    holder.bind.imageView.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//
//
//            })


    }


    fun updatehobbyList(newhobby: List<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newhobby)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = HomeFragmentDirections.actiondetailFragment2(v.tag.toString())
        Navigation.findNavController(v).navigate((action))
    }

    override fun onButtonUpdateClick(v: View) {
        val action = HomeFragmentDirections.actionUpdate(v.tag.toString())
        Navigation.findNavController(v).navigate((action))
    }


}