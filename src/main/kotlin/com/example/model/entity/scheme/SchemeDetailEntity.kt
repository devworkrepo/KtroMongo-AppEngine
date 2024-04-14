package com.example.model.entity.scheme


data class SchemeDetailEntity(
    val minAmount : Int,
    val maxAmount : Int,
    val commissionType : String,
    val retailerCommission : String,
    val distributorCommission : String,
    val masterDistributorCommission : String,
    val adminCommission : String
)