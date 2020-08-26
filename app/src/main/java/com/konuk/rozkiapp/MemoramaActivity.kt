package com.konuk.rozkiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoox.memorama.Chip
import com.xoox.memorama.MAdapter
import java.util.*


class MemoramaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama2)
        val rv = findViewById<RecyclerView>(R.id.recyclerView1)
        rv.setHasFixedSize(true)
        var grid = GridLayoutManager(this, 4)
        rv.layoutManager = grid

        val chips = ArrayList<Chip>()
        for (i in 1..12) {
            chips.add(Chip(R.mipmap.ic_launcher))
        }

        var adapter = MAdapter(chips)
        rv.adapter = adapter
    }
}
