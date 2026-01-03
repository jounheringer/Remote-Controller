package com.reringuy.remotecontroller.data

import android.content.Context
import android.hardware.ConsumerIrManager
import com.reringuy.remotecontroller.data.pattern.samsungPatternConverter
import com.reringuy.remotecontroller.model.IrCode

const val frequency = 38_000

class IrTransmitter(
    context: Context,
) {
    private val irManager =
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    fun hasIrEmitter(): Boolean = irManager.hasIrEmitter()

    fun send(code: IrCode) {
        irManager.transmit(frequency, samsungPatternConverter(code))
    }
}