package com.example.nasapictures.listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasapictures.R
import com.example.nasapictures.details.DetailFragment
import com.example.nasapictures.model.Pictures
import com.example.nasapictures.utils.SpacesItemDecoration
import com.example.nasapictures.utils.Utils
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

import kotlin.collections.ArrayList


class ListFragment : Fragment() ,ListAdapter.OnPicClick
{

    private val TAG: String? = ListFragment.javaClass.simpleName
    private lateinit var adapter: ListAdapter
    private  var pictureslist =  ArrayList<Pictures>()
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
        adapter = ListAdapter(activity!!,this,pictureslist)
        picturerv.layoutManager = GridLayoutManager(activity!!,2)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        picturerv.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        picturerv.adapter = adapter
        addItemFromjson()
    }

    private fun addItemFromjson()
    {
        pictureslist.clear()
        try {
            val jsonDataString: String = Utils.readJSONDataFromFile(activity!!)
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val url = itemObj.getString("url")
                val title = itemObj.getString("title")
                val explanation = itemObj.getString("explanation")
                val hdurl = itemObj.getString("hdurl")

                val picture= Pictures(url = url,title = title,explanation = explanation,hdurl = hdurl)
                pictureslist.add(picture)
            }
            pictureslist.reverse()
            adapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        }
    }


    override fun onPicClick(pos: Int) {
        val detailsFragment = DetailFragment()
        val args = Bundle()
        args.putString("title",pictureslist[pos].title)
        args.putString("explanation",pictureslist[pos].explanation)
        args.putString("hdurl",pictureslist[pos].hdurl)
        detailsFragment.arguments = args
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.content, detailsFragment)
            .addToBackStack("detail")
            .commit()
    }

}