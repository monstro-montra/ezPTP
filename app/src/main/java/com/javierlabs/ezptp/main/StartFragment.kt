package com.javierlabs.ezptp.main

import android.os.Bundle
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

        var equipment: MutableList<Equipment> = mutableListOf()
        equipment.add(element = Equipment(80100, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80105, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80110, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80115, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80120, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80125, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80130, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80135, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80140, "Conveyor", "CP80"))
        equipment.add(element = Equipment(80145, "Conveyor", "CP80"))

        for(element: Equipment in equipment){
            db.collection("equipment").document(element.equipmentID.toString()).set(element)
        }

        val equipmentIDs: MutableList<Int?> = mutableListOf()
        for (element: Equipment in equipment){
            equipmentIDs.add(element.equipmentID)
        }
        val adapter = activity?.let {
            ArrayAdapter<Int>(
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