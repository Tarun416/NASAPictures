package com.example.nasapictures.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasapictures.R
import com.example.nasapictures.details.DetailFragment
import com.example.nasapictures.model.Pictures
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() ,ListAdapter.OnPicClick
{

    private lateinit var adapter: ListAdapter
    private lateinit var pictures: List<Pictures>
    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListAdapter(activity!!,this)
        picturerv.layoutManager = LinearLayoutManager(activity!!)
        picturerv.adapter = adapter
    }

    override fun onClick(pos: Int) {
        val detailsFragment = DetailFragment()
        val args = Bundle()
      /*  args.putString("title",planetaryResponse[pos].title)
        args.putString("explanation",planetaryResponse[pos].explanation)
        args.putString("image",planetaryResponse[pos].hdurl)
        args.putString("version",planetaryResponse[pos].serviceVersion)*/
        detailsFragment.arguments = args
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.content, detailsFragment)
            .addToBackStack("detail")
            .commit()
    }

}