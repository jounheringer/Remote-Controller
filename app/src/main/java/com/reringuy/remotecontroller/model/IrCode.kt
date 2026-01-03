package com.reringuy.remotecontroller.model

data class IrCode(
    val protocol: String,
    val bits: Int,
    val data: Long
)
