package com.example.nasapictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasapictures.listing.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ListFragment.newInstance())
            .commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

}