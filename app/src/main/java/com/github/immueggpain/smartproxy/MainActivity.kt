package com.github.immueggpain.smartproxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user touches the button */
    fun startProxy(view: View) {
        try {
            val etLocalIp: EditText = findViewById(R.id.localIp)
            val etLocalPort: EditText = findViewById(R.id.localPort)
            val etServerIp: EditText = findViewById(R.id.serverIp)
            val etServerPort: EditText = findViewById(R.id.serverPort)
            val etPassword: EditText = findViewById(R.id.password)

            // run sp client
            val settings = Launcher.ClientSettings()
            settings.local_listen_ip = etLocalIp.text.toString()
            settings.local_listen_port = Integer.parseInt(etLocalPort.text.toString())
            settings.server_ip = etServerIp.text.toString()
            settings.server_port = Integer.parseInt(etServerPort.text.toString())
            settings.password = etPassword.text.toString()
            settings.logfile = "smartproxy.log"
            Smartproxy().run(settings)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
