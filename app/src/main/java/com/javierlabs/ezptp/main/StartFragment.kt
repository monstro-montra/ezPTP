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
import kotlinx.coroutines.handleCoroutineException

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

        db.collection("equipment")
            .get()
            .addOnSuccessListener { documents ->
                var equipment: Equipment
                for (document in documents){
                    equipment = document.toObject(Equipment::class.java)
                    equipmentList.add(equipment)
                    Log.d(TAG, "${document.id} => ${document.data}")
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

                    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
                        Toast.makeText(activity,
                            "You selected ${adapterView.getItemAtPosition(position)}",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
            .addOnFailureListener{exception ->
                Log.w(TAG, "Error getting Documents: ", exception)
            }




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