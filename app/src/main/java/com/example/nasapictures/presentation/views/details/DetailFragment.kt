package com.example.nasapictures.presentation.views.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nasapictures.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
             .load(arguments?.getString("hdurl"))
             .placeholder(R.drawable.placeholder)
             .into(hdimage)
        title.text = arguments?.getString("title")
        explanation.text = arguments?.getString("explanation")

    }

}