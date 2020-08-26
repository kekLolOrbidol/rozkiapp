package com.konuk.rozkiapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.jaredrummler.android.colorpicker.ColorShape

class CustomizeActivity : AppCompatActivity(), ColorPickerDialogListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)
        findViewById<ImageView>(R.id.backImg).setOnClickListener{ onBackPressed() }
        getColorFromSp()
    }

    fun selectColor(view : View){
        when(view.id){
            R.id.selectColor -> createColorPickerDialog(1)
            R.id.selectOColor -> createColorPickerDialog(2)
            R.id.selectXColor -> createColorPickerDialog(3)
            R.id.resetBtn -> reset()
        }
        //createColorPickerDialog(1)
    }

    fun reset(){
        saveColorToSP(-1, "BACKGROUND")
        saveColorToSP(-1, "XCOLOR")
        saveColorToSP(-1, "OCOLOR")
        val intent = Intent(this, CustomizeActivity::class.java)
        startActivity(intent)
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getColorFromSp(){
        val pref = getSharedPreferences("BACKGROUND", Context.MODE_PRIVATE)
        val pref2 = getSharedPreferences("OCOLOR", Context.MODE_PRIVATE)
        val pref3 = getSharedPreferences("XCOLOR", Context.MODE_PRIVATE)
        var color = pref.getInt("color", -1)
        if(color != -1){
            findViewById<Button>(R.id.selectColor).setBackgroundColor(color)
        }
        color = pref2.getInt("color", -1)
        if(color != -1) findViewById<Button>(R.id.selectOColor).setBackgroundColor(color)
        color = pref3.getInt("color", -1)
        if(color != -1) findViewById<Button>(R.id.selectXColor).setBackgroundColor(color)
    }

    private fun createColorPickerDialog(id: Int) {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(id)
                .show(this)
// полный список атрибутов класса ColorPickerDialog смотрите ниже
    }

    override fun onDialogDismissed(dialogId: Int) {
        Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onColorSelected(dialogId: Int, color: Int) {
        when(dialogId){
            1 ->{
                saveColorToSP(color, "BACKGROUND")
                findViewById<Button>(R.id.selectColor).setBackgroundColor(color)
            }
            2 -> {
                saveColorToSP(color, "OCOLOR")
                findViewById<Button>(R.id.selectOColor).setBackgroundColor(color)
            }
            3 ->{
                saveColorToSP(color, "XCOLOR")
                findViewById<Button>(R.id.selectXColor).setBackgroundColor(color)
            }
                //findViewById<ConstraintLayout>(R.id.backMenu).setBackgroundColor(color)
                //window.statusBarColor = color
        }
//
    }

    fun saveColorToSP(color : Int, action : String){
        val pref = getSharedPreferences(action, Context.MODE_PRIVATE)
        pref.edit().putInt("color", color).apply()
    }
}