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
                RemoteControllerUI()
            }
        }
    }

    override fun irNotAvailable() {
        Toast.makeText(this, "IR Not Available", Toast.LENGTH_SHORT).show()
    }
}