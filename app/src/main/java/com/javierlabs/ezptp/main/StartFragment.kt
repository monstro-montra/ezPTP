package com.javierlabs.ezptp.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.data.JSONUtils
import com.javierlabs.ezptp.databinding.FragmentStartBinding

private val equipmentCollectionReference = Firebase.firestore.collection("equipment")
class StartFragment : Fragment() {
    private var fragmentStartBinding: FragmentStartBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStartBinding.bind(view)
        fragmentStartBinding = binding
        val spinner = binding.equipmentsSpinner

        val equipment = activity?.let { JSONUtils.getJsonDataFromAsset(it) }

        equipment?.forEach {
            Log.e("Data Equipment", it.toString())
        }

//        val adapter = activity?.let {
//            ArrayAdapter<Int>(
//                it,
//                android.R.layout.simple_spinner_item,
//                equipmentIDs
//            )
//        }
//
//        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long){
                Toast.makeText(activity,
                "You selected ${adapterView.getItemAtPosition(position)}",
                Toast.LENGTH_LONG).show()
            }
        }






    }

}