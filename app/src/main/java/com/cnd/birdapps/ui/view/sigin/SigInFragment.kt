package com.cnd.birdapps.ui.view.sigin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cnd.birdapps.R

class SigInFragment : Fragment() {

    companion object {
        fun newInstance() = SigInFragment()
    }

    private lateinit var viewModel: SigInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sig_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SigInViewModel::class.java)
        // TODO: Use the ViewModel
    }

}