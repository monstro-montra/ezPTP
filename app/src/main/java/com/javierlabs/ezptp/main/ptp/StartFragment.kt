package com.javierlabs.ezptp.main.ptp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
        return inflater.inflate(R.layout.ptp_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStartBinding.bind(view)
        fragmentStartBinding = binding
        val spinner = binding.equipmentsSpinner

        fetchEquipmentData { equipmentList ->
            setupSpinner(spinner, equipmentList)
        }
    }

    // fetch from Firestore database and return a mutable list of Equipment objects upon successful fetch. otherwise throw an error log
    private fun fetchEquipmentData(onSuccess: (List<Equipment>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val equipmentList: MutableList<Equipment> = mutableListOf()
        db.collection("equipment")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val equipment = document.toObject(Equipment::class.java)
                    equipmentList.add(equipment)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                onSuccess(equipmentList)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    //create the adapter for the spinner that will be populated from the list of equipmentIDs
    private fun createAdapter(equipmentList: List<Equipment>): ArrayAdapter <String>? {
        val equipmentIDs: MutableList<String?> = mutableListOf()
        equipmentList.forEach{ equipment: Equipment ->
            equipmentIDs.add(equipment.equipmentID) // add the equipmentID at index i from the equipmentList to the equipmentIDs list.
        }

        return activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                equipmentIDs
            )
        }
    }

    //setup the spinner using the adapter. the adapter populates spinner, this function will set the functionality for each item.
    private fun setupSpinner(spinner: Spinner, equipmentList: List<Equipment>){
        val adapter = createAdapter(equipmentList)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (adapterView != null) {
                    Toast.makeText(activity,
                        "You selected ${adapterView.getItemAtPosition(position)}",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}