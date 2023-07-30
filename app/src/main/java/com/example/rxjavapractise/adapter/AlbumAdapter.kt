package com.example.rxjavapractise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavapractise.databinding.RecylervcarditemBinding
import com.example.rxjavapractise.datamodel.albumResponse

class AlbumAdapter(var context: Context,var albumlist:ArrayList<albumResponse>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
       var binding = RecylervcarditemBinding.inflate(LayoutInflater.from(context),parent,false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albumlist.size
    }
    fun setData(albumlist: ArrayList<albumResponse>){
        this.albumlist = albumlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.TvID.text = albumlist[position].id.toString()
        holder.binding.TvName.text = albumlist[position].title
        holder.binding.TvuserID.text = albumlist[position].userId.toString()
    }






    inner class AlbumViewHolder( var binding: RecylervcarditemBinding): RecyclerView.ViewHolder(binding.root) {

    }
}