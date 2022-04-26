package com.example.kotlin2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin2.Haber
import com.example.kotlin2.R
import com.example.kotlin2.util.downloadFromUrl
import com.example.kotlin2.util.pladeHolderProgressBar
import com.example.kotlin2.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.row.*


class DetailsFragment : Fragment() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments.let {
            var gelenID = DetailsFragmentArgs.fromBundle(it!!).uID

        }


        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        viewModel.getDetail()

        observeLiveData2()

    }

    private fun observeLiveData2() {

        viewModel.detailsHaber.observe(viewLifecycleOwner, Observer { haber ->
            haber?.let {

                detailtext.text=haber.name
                detailsource.text=haber.source
                detailescription.text=haber.description

            }
        })
    }


}