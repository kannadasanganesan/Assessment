package com.example.searchindictionary.ui.src.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.searchindictionary.databinding.UserItemBinding
import com.example.searchindictionary.ui.src.data.models.Lfs
import com.example.searchindictionary.ui.src.data.models.Searchresultmodel


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var searchDataList: MutableList<Searchresultmodel> = ArrayList()
    private var lfsResult: MutableList<Lfs> = ArrayList()
    //for data binding
    lateinit var binding: UserItemBinding
    fun setResult(users: MutableList<Searchresultmodel>, lfs: MutableList<Lfs>) {
        searchDataList.clear()
        lfsResult.clear()
        searchDataList.addAll(users)
        lfsResult.addAll(lfs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return lfsResult.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.parent.resultBinding = lfsResult[position]

    }

    class UserViewHolder(var parent: UserItemBinding) : ViewHolder(parent.root) {

    }
}