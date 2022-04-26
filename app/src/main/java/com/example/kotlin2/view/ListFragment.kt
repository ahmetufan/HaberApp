package com.example.kotlin2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin2.R
import com.example.kotlin2.adaptor.Adaptery
import com.example.kotlin2.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private lateinit var adaptery: Adaptery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(ListViewModel::class.java)

        //viewModel.refreshing()

        //Recyclerview
        recyclerview.layoutManager=GridLayoutManager(context,2)
        adaptery= Adaptery(arrayListOf())
        recyclerview.adapter=adaptery

        swipeRefreshLayout.setOnRefreshListener {
            recyclerview.visibility=View.GONE
            haberError.visibility=View.GONE
            haberProgressBar.visibility=View.VISIBLE
            viewModel.refreshing()

            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.habers.observe(viewLifecycleOwner, Observer { habers ->
            habers?.let {
                recyclerview.visibility=View.VISIBLE
                adaptery.updateHaberList(habers)
            }
        })

        viewModel.haberError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it) {
                    haberError.visibility=View.VISIBLE
                } else {
                    haberError.visibility=View.GONE
                }
            }
        })

        viewModel.haberLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    recyclerview.visibility=View.GONE
                    haberError.visibility=View.GONE
                    haberProgressBar.visibility=View.VISIBLE
                } else {
                    haberProgressBar.visibility=View.GONE
                }
            }
        })

    }
}