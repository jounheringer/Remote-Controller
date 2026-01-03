package com.reringuy.remotecontroller.presenter

import com.reringuy.remotecontroller.utils.IRCommand

interface RemoteControllerContract {
    interface View {
        fun irNotAvailable()
    }

    interface Presenter {
        fun onCommand(irCommand: IRCommand)
        fun onDestroy()
    }
}