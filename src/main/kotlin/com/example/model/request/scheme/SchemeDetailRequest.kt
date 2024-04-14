package com.example.model.request.scheme

import com.example.model.entity.scheme.SchemeDetailEntity
import kotlinx.serialization.Serializable
import kotlin.math.min


@Serializable
data class SchemeDetailRequest(
    val minAmount : Int,
    val maxAmount : Int,
    val commissionType : String,
    val retailerCommission : String,
    val distributorCommission : String,
    val masterDistributorCommission : String,
    val adminCommission : String
){
    fun toSchemeDetailEntity()=SchemeDetailEntity(
        minAmount = minAmount,
        maxAmount = maxAmount,
        commissionType = commissionType,
        retailerCommission = retailerCommission,
        distributorCommission = distributorCommission,
        masterDistributorCommission = masterDistributorCommission,
        adminCommission = adminCommission
    )
}