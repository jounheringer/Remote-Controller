package com.reringuy.remotecontroller.presenter

import com.reringuy.remotecontroller.data.IrTransmitter
import com.reringuy.remotecontroller.utils.IRCommand

class RemoteControllerPresenter(
    private var view: RemoteControllerContract.View,
    private val transmitter: IrTransmitter
) : RemoteControllerContract.Presenter {
    override fun onCommand(irCommand: IRCommand) {
        if (!transmitter.hasIrEmitter())
            view.irNotAvailable()
        else{
            transmitter.send(irCommand.irCode)
        }
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}