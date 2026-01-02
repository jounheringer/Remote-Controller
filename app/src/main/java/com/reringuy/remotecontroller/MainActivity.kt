package com.reringuy.remotecontroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.reringuy.remotecontroller.data.IrTransmitter
import com.reringuy.remotecontroller.presenter.RemoteControllerContract
import com.reringuy.remotecontroller.presenter.RemoteControllerPresenter
import com.reringuy.remotecontroller.ui.screens.RemoteControllerUI
import com.reringuy.remotecontroller.ui.theme.RemoteControllerTheme
import com.reringuy.remotecontroller.utils.IRCommand

class MainActivity : ComponentActivity(), RemoteControllerContract.View {

    lateinit var presenter: RemoteControllerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RemoteControllerPresenter(
            this,
            IrTransmitter(this)
        )

        enableEdgeToEdge()
        setContent {
            RemoteControllerTheme {
                RemoteControllerUI(
                    onTriggerPower = { presenter.onCommand(IRCommand.ON_OFF) },
                    onMove = presenter::onCommand,
                    onSelect = { presenter.onCommand(IRCommand.SELECT) },
                    onVolumeUp = { presenter.onCommand(IRCommand.VOL_PLUS) },
                    onVolumeDown = { presenter.onCommand(IRCommand.VOL_MINUS) },
                    onMute = { presenter.onCommand(IRCommand.MUTE) },
                    onChannelPlus = { presenter.onCommand(IRCommand.CH_PLUS) },
                    onChannelMinus = { presenter.onCommand(IRCommand.CH_MINUS) },
                    onReturn = { presenter.onCommand(IRCommand.RETURN) }
                )
            }
        }
    }

    override fun irNotAvailable() {
        Toast.makeText(this, "IR Not Available", Toast.LENGTH_SHORT).show()
    }
}