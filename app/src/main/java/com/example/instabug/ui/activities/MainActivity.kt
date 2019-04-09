package com.example.instabug.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.example.instabug.BaseActivity
import com.example.instabug.R
import com.example.instabug.fcm.RegistrationIntentService
import com.instabug.bug.BugReporting
import com.instabug.featuresrequest.FeatureRequests
import com.instabug.library.Instabug
import com.instabug.library.logging.InstabugLog
import com.instabug.library.logging.InstabugNetworkLog
import com.instabug.library.ui.onboarding.WelcomeMessage
import com.instabug.survey.Surveys
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instabug logs
        InstabugLog.d("MainActivity - Created")

        registerFCM()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        doNetworkRequest()
    }

    private fun registerFCM() {
        // Start IntentService to register this application with FCM.
        val intent = Intent(this, RegistrationIntentService::class.java)
        startService(intent)
    }

    fun onShowLiveOnboardingMessageClicked(view: View) {
        Instabug.showWelcomeMessage(WelcomeMessage.State.LIVE)
    }

    fun onShowBetaOnboardingMessageClicked(view: View) {
        Instabug.showWelcomeMessage(WelcomeMessage.State.BETA)
    }

    fun onShowInstabugClicked(view: View) {
        BugReporting.invoke()
    }

    fun onCrashTheAppClicked(view: View) {
        AlertDialog.Builder(this@MainActivity)
                .setMessage("Are you sure that want to crash the app?")
                .setPositiveButton("Yes") { dialog, which ->
                    val arrayOfIntegers = intArrayOf(0, 1, 2, 3)
                    val i = arrayOfIntegers[5]
                }
                .setNegativeButton("No", null)
                .show()
    }

    fun showNPSSurvey(view: View) {
        Surveys.showSurvey("ulUaFocMCejDr3Ldd8VBaA")
    }

    fun showMultipleQuestionSurvey(view: View) {
        Surveys.showSurvey("AGI5OH47k3eEAIKj-yKDWA")
    }

    fun showFeatureRequests(view: View) {
        FeatureRequests.show()
    }

    fun onShowSettingsClicked(view: View) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun doNetworkRequest() {
        FetchMoviesData().execute()
    }

    private inner class FetchMoviesData : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String? {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            var urlConnection: HttpURLConnection? = null
            var reader: BufferedReader? = null

            // Will contain the raw JSON response as a string.
            var moviesJsonStr: String? = null

            try {

                val url = URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=a491e06af68296a1fad86c70235e98f9 ")
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.doOutput = true
                urlConnection.requestMethod = "POST"
                urlConnection.useCaches = false
                urlConnection.connectTimeout = 10000
                urlConnection.readTimeout = 10000
                urlConnection.setRequestProperty("Content-Type", "application/json")
                urlConnection.connect()

                //Create JSONObject here
                val jsonParam = JSONObject()
                try {
                    jsonParam.put("ID", "25")
                    jsonParam.put("description", "Real")
                    jsonParam.put("enable", "true")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }


                val out = OutputStreamWriter(urlConnection.outputStream)
                out.write(jsonParam.toString())
                out.close()

                // Read the input stream into a String
                val inputStream = urlConnection.inputStream
                val buffer = StringBuffer()
                if (inputStream == null) {
                    // Nothing to do.
                    return null

                }
                reader = BufferedReader(InputStreamReader(inputStream))

                var line = reader.readLine()
                while (line != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n")
                    line = reader.readLine()
                }

                if (buffer.isEmpty()) {
                    // Stream was empty.  No point in parsing.
                    return null
                }
                moviesJsonStr = buffer.toString()

                //logging network request to instabug
                val networkLog = InstabugNetworkLog()
                networkLog.log(urlConnection, jsonParam.toString(), moviesJsonStr)

                return moviesJsonStr
            } catch (e: IOException) {
                Log.e("MainActivity", "Error ", e)
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null
            } finally {
                urlConnection?.disconnect()
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (e: IOException) {
                        Log.e("MainActivity", "Error closing stream", e)
                    }

                }
            }
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            Log.d("Response", s + "")
        }
    }

}
