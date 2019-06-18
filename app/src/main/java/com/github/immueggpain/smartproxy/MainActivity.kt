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
            settings.local_listen_ip = "127.0.0.1"
            settings.local_listen_port = Integer.parseInt(cmd.getOptionValue(local_listen_port, "1083"))
            settings.server_ip = cmd.getOptionValue(server_ip)
            settings.server_port = Integer.parseInt(cmd.getOptionValue(server_port))
            settings.password = cmd.getOptionValue(password)
            settings.logfile = "smartproxy.log"
            Smartproxy().run(settings)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
