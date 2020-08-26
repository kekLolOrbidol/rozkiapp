package com.xoox.memorama

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.konuk.rozkiapp.R
import java.util.*

class MAdapter(val chips : ArrayList<Chip>) : RecyclerView.Adapter<MAdapter.ChipViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChipViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row, p0, false)
        return ChipViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chips.size
    }

    override fun onBindViewHolder(p0: ChipViewHolder, p1: Int) {
        p0.imageView.setImageResource(chips[p1].idImg)
    }

    inner class ChipViewHolder(item : View) : RecyclerView.ViewHolder(item) {
        val imageView = item.findViewById<ImageView>(R.id.chip)

        init {
            item.setOnClickListener {
                imageView.setImageResource(R.drawable.doggo)
            }
        }
    }
}