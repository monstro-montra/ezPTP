package com.javierlabs.ezptp.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.data.Equipment
import com.javierlabs.ezptp.databinding.FragmentStartBinding

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
        val db = FirebaseFirestore.getInstance()
        val equipmentList: MutableList<Equipment> = mutableListOf()

        db.collection("equipment").addSnapshotListener{ snapshot, e ->
            //error logic
            if (e != null) {
                Log.w(TAG, ("Listen Failed"))
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val documents = snapshot.documents
                documents.forEach{
                    val equipment = it.toObject(Equipment::class.java)
                    if  (equipment != null) {
                        equipment.equipmentID = it.id
                        equipmentList.add(equipment)
                    }
                }
            }
        }
        val equipmentIDs: MutableList<String?> = mutableListOf()
        equipmentList.forEach{ equipment: Equipment ->
            equipmentIDs.add(equipment.equipmentID)
        }

        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                equipmentIDs
            )
        }

        spinner.adapter = adapter
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