package com.javierlabs.ezptp.main.ptp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.fragment.app.activityViewModels
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.data.SafetyPlanOption
import com.javierlabs.ezptp.databinding.PtpFragmentFirstAnswerBinding
import com.javierlabs.ezptp.viewmodel.SharedViewModel

class FirstAnswerFragment : Fragment() {
    private var ptpFragmentFirstAnswerBinding: PtpFragmentFirstAnswerBinding? = null
    private lateinit var checkboxContainer: LinearLayout
    private lateinit var hazardEnergySourceOption : SafetyPlanOption
    private lateinit var nextBtn: Button
    private val sharedViewModel: SharedViewModel by activityViewModels()
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

        hazardEnergySourceOption = SafetyPlanOption("Hazardous Energy Source", listOf(
            "Electrical",
            "Pneumatic",
            "Chemical",
            "Gravity",
            "Hydraulic",
            "Mechanical/Hazardous Motion",
            "Robotics",
            "Stored Energy")
        )




        nextBtn.setOnClickListener {
            if(binding.hazardousEnergySourceCb.isChecked){
                hazardsTV.text = "Hazardous Energy Source: What is the specific hazard? (Select all that apply)."
                displayCheckboxes(hazardEnergySourceOption)

            }
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

//    private fun initOptions() : SafetyPlanOption {
//
//    }
}