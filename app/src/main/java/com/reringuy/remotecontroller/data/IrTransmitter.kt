package com.reringuy.remotecontroller.data

import android.content.Context
import android.hardware.ConsumerIrManager
import com.reringuy.remotecontroller.model.IrCode

class IrTransmitter(
    context: Context
) {

    private val irManager =
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    fun send(code: IrCode) {
        if (irManager.hasIrEmitter()) {
            irManager.transmit(code.frequency, code.pattern)
        }
    }
}