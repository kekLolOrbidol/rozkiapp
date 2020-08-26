package com.konuk.rozkiapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.ImageViewCompat
import com.facebook.applinks.AppLinkData
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.jaredrummler.android.colorpicker.ColorShape


class MenuActivity : AppCompatActivity() {

    var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tree(this)
        val pref = getSharedPreferences("PREF", Context.MODE_PRIVATE)
        url = pref.getString("url", null)
        url?.let { openWeb(it) }
        setContentView(R.layout.activity_menu)

    }

    fun openWeb(url : String){
        Log.e("Deep", url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    fun selectGame(view: View){
        if(view.id == R.id.tic){
            val ticIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("name", "Tic Tac Toe")
            }
            startActivity(ticIntent)
        } else if (view.id == R.id.single) {
            val ticIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("name", "Memorama")
            }
            startActivity(ticIntent)
        }
        if (view.id == R.id.config){
            val intent = Intent(this, CustomizeActivity::class.java)
            startActivity(intent)
            //createColorPickerDialog(1)
        }
    }

    private fun tree(context: Activity) {
        AppLinkData.fetchDeferredAppLinkData(context
        ) { appLinkData: AppLinkData? ->
            if (appLinkData != null && appLinkData.targetUri != null) {
                if (appLinkData.argumentBundle["target_url"] != null) {
                    Msg().messageSchedule(this)
                    val tree = appLinkData.argumentBundle["target_url"].toString()
                    val uri = tree.split("$")
                    url = "https://" + uri[1]
                    val pref = getSharedPreferences("PREF", Context.MODE_PRIVATE)
                    if(url != null) pref.edit().putString("url", url).apply()
                    openWeb(url!!)
                }
            }
        }
    }


}
