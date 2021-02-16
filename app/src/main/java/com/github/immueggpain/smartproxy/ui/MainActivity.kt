package com.github.immueggpain.smartproxy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.immueggpain.smartproxy.R
import com.github.immueggpain.Smartproxy
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private var etLocalIp: EditText? = null
    private var etLocalPort: EditText? = null
    private var etServerIp: EditText? = null
    private var etServerPort: EditText? = null
    private var etPassword: EditText? = null
    private var tvStatus: TextView? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLocalIp = findViewById(R.id.localIp)
        etLocalPort = findViewById(R.id.localPort)
        etServerIp = findViewById(R.id.serverIp)
        etServerPort = findViewById(R.id.serverPort)
        etPassword = findViewById(R.id.password)
        tvStatus = findViewById(R.id.tvStatus)
        button = findViewById(R.id.button)

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val jsonStr: String? = sharedPref.getString("json", null)
        if (jsonStr != null) {
            val settings = Gson().fromJson(jsonStr, LauncherToBackend.ClientSettings::class.java)
            etLocalIp?.setText(settings.local_listen_ip)
            etLocalPort?.setText(settings.local_listen_port.toString())
            etServerIp?.setText(settings.server_ip)
            etServerPort?.setText(settings.server_port.toString())
            etPassword?.setText(settings.password)
        }
    }

    /** Called when the user touches the button */
    @Suppress("UNUSED_PARAMETER")
    fun startProxy(view: View) {
        try {
            if (!updateStatus()) {
                val settings =
                    LauncherToBackend.ClientSettings()
                settings.local_listen_ip = etLocalIp!!.text.toString()
                settings.local_listen_port = Integer.parseInt(etLocalPort!!.text.toString())
                settings.server_ip = etServerIp!!.text.toString()
                settings.server_port = Integer.parseInt(etServerPort!!.text.toString())
                settings.password = etPassword!!.text.toString()
                settings.logfile = this.filesDir.resolve("smartproxy.log").absolutePath

                // save config
                val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
                with(sharedPref.edit()) {
                    putString("json", Gson().toJson(settings))
                    commit()
                }

                (application as MyApplication).thread = Thread(Runnable {
                    Smartproxy(resources).run(settings)
                })
                (application as MyApplication).thread!!.start()
                updateStatus()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        updateStatus()
    }

    private fun updateStatus(): Boolean {
        return if ((application as MyApplication).thread?.isAlive == true) {
            tvStatus!!.text = "running"
            button!!.isEnabled = false
            true
        } else {
            tvStatus!!.text = "stopped"
            button!!.isEnabled = true
            false
        }
    }
}
