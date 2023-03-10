package com.javierlabs.ezptp.data
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


data class EquipmentModel(
    @SerializedName("equipment")
    var equipment:List<Equipment>
){
    data class Equipment(
        @SerializedName("type")
        var type: String? = null,

        @SerializedName("equipmentID")
        var equipment_id: Int? = null,

        @SerializedName("panel")
        val panel_id: String? = null,
    )

}



