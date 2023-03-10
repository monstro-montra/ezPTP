package com.javierlabs.ezptp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JSONUtils {
    companion object {
        fun getJsonDataFromAsset(context: Context): List<EquipmentModel.Equipment>{
            val equipmentJson = context.assets.open("equipment.json").bufferedReader().use { it.readText() }
            val equipment = Gson().fromJson(equipmentJson, EquipmentModel::class.java)

            return equipment.equipment
        }
    }
}