package com.konuk.rozkiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(var buttons : List<Button>, val callback : Callback) : RecyclerView.Adapter<MenuAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false))

    override fun getItemCount() = buttons.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(buttons[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Button) {
            item.setOnClickListener { p0 ->
                when(p0?.id){

                }
            }

        }
    }
    interface Callback {
        fun onItemClicked(item: Button)
    }


}
