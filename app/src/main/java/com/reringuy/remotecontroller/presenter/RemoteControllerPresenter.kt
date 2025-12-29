package com.reringuy.remotecontroller.presenter

import com.reringuy.remotecontroller.data.IrTransmitter
import com.reringuy.remotecontroller.utils.IRCommand

class RemoteControllerPresenter(
    private var view: RemoteControllerContract.View,
    private val transmitter: IrTransmitter
) : RemoteControllerContract.Presenter {
    override fun onCommand(irCommand: IRCommand) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}