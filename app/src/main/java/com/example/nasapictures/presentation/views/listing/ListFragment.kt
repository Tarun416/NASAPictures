package com.example.nasapictures.presentation.views.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasapictures.R
import com.example.nasapictures.data.entity.mapper.PictureMapper
import com.example.nasapictures.data.local.PictureLocal
import com.example.nasapictures.data.local.PictureLocalImpl
import com.example.nasapictures.data.repository.PictureDataRepository
import com.example.nasapictures.data.repository.datasource.PictureDataStoreFactory
import com.example.nasapictures.domain.interactor.GetPictures
import com.example.nasapictures.domain.model.Picture
import com.example.nasapictures.presentation.views.details.DetailFragment
import com.example.nasapictures.presentation.utils.ResourceState
import com.example.nasapictures.presentation.views.adapter.ListAdapter
import com.example.nasapictures.presentation.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_list.*

import kotlin.collections.ArrayList


class ListFragment : Fragment() , ListAdapter.OnPicClick
{

    private lateinit var adapter: ListAdapter
    private  var pictureslist =  ArrayList<Picture>()
    private lateinit var viewModel : ListViewModel

    private lateinit var pictureMapper: PictureMapper
    private lateinit var pictureLocal : PictureLocal
    private lateinit var pictureDataSourceFactory: PictureDataStoreFactory
    private lateinit var pictureDataRepository: PictureDataRepository

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
        adapter = ListAdapter(activity!!, this, pictureslist)
        picturerv.layoutManager = GridLayoutManager(activity!!,2)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        picturerv.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        picturerv.adapter = adapter

        initViewModel()
    }

    private fun initViewModel()
    {
        pictureMapper = PictureMapper()
        pictureLocal = PictureLocalImpl()
        pictureDataSourceFactory = PictureDataStoreFactory(pictureLocal)
        pictureDataRepository = PictureDataRepository(pictureDataSourceFactory,pictureMapper)
        val getPicture = GetPictures(pictureDataRepository)
        viewModel = ListViewModel(getPicture)
        viewModel.pictureLiveData.observe(this,androidx.lifecycle.Observer{
            it.let {
                handleData(it.status,it.data,it.message)
            }
        })
        viewModel.getPictures()
    }

    private fun handleData(status: ResourceState, data: List<Picture>?, message: String?) {
        if(status ==ResourceState.SUCCESS) {
            pictureslist.clear()
            pictureslist.addAll(data!!)
            pictureslist.reverse()
            adapter.notifyDataSetChanged()
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