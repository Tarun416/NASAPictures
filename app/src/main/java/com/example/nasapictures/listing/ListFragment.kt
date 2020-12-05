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
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
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
        try {
            val jsonDataString: String = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val url = itemObj.getString("url")

                val picture= Pictures(url = url)
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

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String? = null
            inputStream = resources.openRawResource(R.raw.picture)
            val bufferedReader = BufferedReader(
                    InputStreamReader(inputStream, "UTF-8"))
            while (bufferedReader.readLine().also({ jsonString = it }) != null) {
                builder.append(jsonString)
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return String(builder)
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