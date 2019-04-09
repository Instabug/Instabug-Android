package com.example.instabug.ui.activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.instabug.BaseActivity
import com.example.instabug.R
import com.instabug.bug.BugReporting
import com.instabug.library.Instabug
import com.instabug.library.InstabugColorTheme
import com.instabug.library.invocation.InstabugInvocationEvent
import petrov.kristiyan.colorpicker.ColorPicker
import java.util.*

class SettingsActivity : BaseActivity() {

    private val invocationEvents = getInvocationEventsNames(InstabugInvocationEvent::class.java)
    private val invocationEventsState = booleanArrayOf(false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        for (i in invocationEvents.indices) {
            invocationEvents[i] = invocationEvents[i].replace("_", " ")
        }
    }

    fun onShowInvocationEventsClicked(view: View) {
        // reset events
        for (i in invocationEventsState.indices) {
            invocationEventsState[i] = false
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Invocation Events")
        builder.setMultiChoiceItems(invocationEvents, null) { dialog, which, isChecked -> invocationEventsState[which] = isChecked }
        builder.setPositiveButton("Done") { dialog, which ->
            val selectedEvents = mutableListOf<InstabugInvocationEvent>()
            for (i in invocationEvents.indices) {
                if (invocationEventsState[i]) {
                    selectedEvents.add(InstabugInvocationEvent.valueOf(invocationEvents[i].toUpperCase().replace(" ", "_")))
                }
            }
            // set new invocation events here
            BugReporting.setInvocationEvents(*selectedEvents.toTypedArray())
        }
        builder.show()
    }

    fun onChangeThemeClicked(view: View) {
        val items = arrayOf<CharSequence>("Light", "Dark")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Theme")
        builder.setItems(items) { dialog, item ->
            when (item) {
                0 -> Instabug.setColorTheme(InstabugColorTheme.InstabugColorThemeLight)
                1 -> Instabug.setColorTheme(InstabugColorTheme.InstabugColorThemeDark)
            }
        }
        builder.show()
    }

    fun onChangePrimaryColorClicked(view: View) {
        val colorPicker = ColorPicker(this)
        colorPicker.show()
        colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
            override fun onChooseColor(position: Int, color: Int) {
                Instabug.setPrimaryColor(color)
            }

            override fun onCancel() {
                // put code
            }
        })

    }

    companion object {

        fun getInvocationEventsNames(e: Class<out Enum<*>>): Array<String> {
            return Arrays.toString(e.enumConstants).replace("^.|.$".toRegex(), "").split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }
    }
}
