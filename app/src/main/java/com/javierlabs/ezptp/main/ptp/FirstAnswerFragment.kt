package com.javierlabs.ezptp.main.ptp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.data.InputType
import com.javierlabs.ezptp.data.SafetyPlanOption
import com.javierlabs.ezptp.databinding.PtpFragmentFirstAnswerBinding

class FirstAnswerFragment : Fragment() {
    private lateinit var checkboxContainer: LinearLayout
    private lateinit var hazardEnergySourceOption : SafetyPlanOption
    private lateinit var workFromHeightsOption : SafetyPlanOption
    private lateinit var nextBtn: Button
    private lateinit var selectedOptions: MutableList<SafetyPlanOption>
    private var currentIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ptp_fragment_first_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val binding = PtpFragmentFirstAnswerBinding.bind(view)
        checkboxContainer = binding.checkBoxesContainer
        nextBtn = binding.nextButton
        val hazardsTV = binding.hazardsTv

        initSafetyPlanOptions()




        nextBtn.setOnClickListener {
            if (currentIndex == 0) {
                selectedOptions = getSelectedOptions(checkboxContainer)
                if(selectedOptions.isNotEmpty()){
                    displayNextOption(hazardsTV)
                    currentIndex++
                }
            } else if (currentIndex < selectedOptions.size){
                displayNextOption(hazardsTV)
                currentIndex++
            } else {
                //TODO
            }

        }
    }

    private fun initSafetyPlanOptions() {
        hazardEnergySourceOption = SafetyPlanOption("Hazardous Energy Source", listOf(
            "Electrical",
            "Pneumatic",
            "Chemical",
            "Gravity",
            "Hydraulic",
            "Mechanical/Hazardous Motion",
            "Robotics",
            "Stored Energy"
        ), InputType.CHECKBOX)

        workFromHeightsOption = SafetyPlanOption("Work From Heights", listOf(
            "Working within 15 feet from an exposed edge of 4 feet or more",
            "Working at an elevated height near the railing of a mezzanine, or other fall hazard",
            "Working on a Catwalk",
            "Working from a Scissors or WAVE lift",
            "Use of PIT and/or Aerial lift",
            "Working from a Platform Ladder",
            "Working from an A-Frame Ladder",
            "Working from Scaffolding",
            "Working on the Roof",
            "Working on Conveyance with Standard Railing",
            "Other"
        ), InputType.CHECKBOX)
    }

    private fun displayNextOption(textView: TextView) {
        if (currentIndex < selectedOptions.size) {
            val option = selectedOptions[currentIndex]
            textView.text = "${option.title}: "
            displayCheckboxes(option)
        }
    }

    private fun displayCheckboxes(option: SafetyPlanOption) {
        checkboxContainer.removeAllViews()

        val inflater = LayoutInflater.from(requireContext())

        for (variable in option.variables){
            // Inflate the CheckBox from the XML layout file
            val checkBox = inflater.inflate(R.layout.checkbox_item, checkboxContainer, false) as CheckBox

            // Set the current variable as text
            checkBox.text = variable

            // Add the CheckBox to the container
            checkboxContainer.addView(checkBox)
        }
    }

    private fun getSelectedOptions(layout: LinearLayout): MutableList<SafetyPlanOption> {
        val options = mutableListOf<SafetyPlanOption>()
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is CheckBox && child.isChecked) {
                when (child.id) {
                    R.id.hazardous_energy_source_cb -> options.add(hazardEnergySourceOption)
                    R.id.work_from_heights_cb -> options.add(workFromHeightsOption)
                    // Add other cases for remaining checkboxes here
                }
            }
        }
        return options
    }

    private fun getSelectedCheckboxesCount(layout: LinearLayout): Int {
        var count = 0
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is CheckBox && child.isChecked) {
                count++
            }
        }
        return count
    }


}