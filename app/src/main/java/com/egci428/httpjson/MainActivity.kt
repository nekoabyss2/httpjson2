package com.egci428.httpjson

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteBtn.setOnClickListener{
            var asynTask = object :AsyncTask<String,String,String>() {
                override fun onPreExecute() {
                    Toast.makeText(this@MainActivity,"Pls wait...",Toast.LENGTH_LONG).show()
                }

                override fun onPostExecute(result: String?) {
                    val quoteTxt = Gson().fromJson(result, Quote::class.java)
                    Log.d("ttt",quoteTxt.quote)
                    txtQuote.text = quoteTxt.quote
                    if(txtQuote.visibility == INVISIBLE){
                        txtQuote.visibility = VISIBLE
                    }
                }

                override fun doInBackground(vararg p0: String?): String {
                    var helper = Helper()
                    return helper.getHTTPData("http://talakis.com/api/quotes/random/")
                }
            }
        }
    }
}
