package dev.vitorramos.tennis

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.io.IOException

class ServerActivity : AppCompatActivity() {
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

        AcceptThread().start()
    }

    private fun manageConnectedSocket(socket: BluetoothSocket) {
    }

    private inner class AcceptThread : Thread() {
        private val mmServerSocket: BluetoothServerSocket?

        init {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            var tmp: BluetoothServerSocket? = null
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(SERVICE_NAME, java.util.UUID.fromString(UUID))
            } catch (ignored: IOException) {
            }

            mmServerSocket = tmp
        }

        override fun run() {
            var socket: BluetoothSocket?
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket!!.accept()
                } catch (e: IOException) {
                    break
                }

                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket(socket)
                    try {
                        mmServerSocket.close()
                    } catch (ignored: IOException) {
                    }

                    break
                }
            }
        }

        /**
         * Will cancel the listening socket, and cause the thread to finish
         */
        fun cancel() {
            try {
                mmServerSocket!!.close()
            } catch (ignored: IOException) {
            }
        }
    }

    companion object {
        private const val UUID = "a648a358-e718-42c8-b538-cb663a06fb97"
        private const val SERVICE_NAME = "Tennis"
    }
}
