package com.example.nasapictures.presentation.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasapictures.R
import com.example.nasapictures.presentation.views.listing.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ListFragment.newInstance())
            .commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

}