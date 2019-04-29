package com.example.changelaunchericon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.pm.PackageManager
import android.content.ComponentName
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var listOfLauncher = listOf<String>("Default", "Launcher1", "Launcher2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDefault = findViewById<View>(R.id.buttonDefault)
        val button1 = findViewById<View>(R.id.button1)
        val button2 = findViewById<View>(R.id.button2)

        buttonDefault.setOnClickListener {
            changeLauncherIcon("Default")
        }

        button1.setOnClickListener {
            changeLauncherIcon("Launcher1")
        }

        button2.setOnClickListener {
            changeLauncherIcon("Launcher2")
        }
    }

    private fun changeLauncherIcon(enabledIcon: String){
        val pm = applicationContext.packageManager

        //enable launcher className
        pm.setComponentEnabledSetting(
            getComponentClassName(enabledIcon),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        // disable rest of list
        val disabledIconList = listOfLauncher.filterNot { it == enabledIcon }
        for(disabledIcon in disabledIconList){
            pm.setComponentEnabledSetting(
                getComponentClassName(disabledIcon),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }

        Toast.makeText(this, "Icon will be changed in 10 seconds!", Toast.LENGTH_LONG).show()
    }

    private fun getComponentClassName(name: String): ComponentName{
        return ComponentName(
            "com.example.changelaunchericon",
            "com.example.changelaunchericon.$name"
        )
    }
}
