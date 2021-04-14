package com.cnd.birdapps.ui.view.morphology

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cnd.birdapps.R
import com.cnd.birdapps.ui.viewmodels.MorphologyViewModel

class MorphologyFragment : Fragment() {

    companion object {
        fun newInstance() = MorphologyFragment()
    }

    private lateinit var viewModel: MorphologyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_morphology, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MorphologyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}