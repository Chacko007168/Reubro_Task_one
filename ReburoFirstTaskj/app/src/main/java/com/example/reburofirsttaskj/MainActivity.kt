package com.example.reburofirsttaskj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progbar.visibility = View.GONE


        fun showLoading() {
            progbar?.visibility = View.VISIBLE
        }
        fun hideLoading() {
            progbar?.visibility = View.GONE
        }

        button_login.setOnClickListener {
            var email = edittext_email.text.toString().trim()
            var password = edittext_password.text.toString().trim()
            var userType: Int = 1
            var providerType: Int = 1


            if (email.isEmpty()) {
                edittext_email.error = "Email required"
                edittext_email.requestFocus()
                return@setOnClickListener
                            hideLoading()
            }
            if (password.isEmpty()) {
                edittext_password.error = "Password required"
                edittext_password.requestFocus()
                return@setOnClickListener
             hideLoading()
            }

            showLoading()


            Retrofit.instance.userLogin(email, password, userType, providerType)
                .enqueue(object : Callback<com.example.reburofirsttaskj.Response> {
                    override fun onResponse(
                        call: Call<com.example.reburofirsttaskj.Response>,
                        response: Response<com.example.reburofirsttaskj.Response>
                    ) {


                        if (response.body()?.status == "success") {
                            Toast.makeText(
                                applicationContext,
                                "Login Success",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    HomeActivity::class.java
                                )
                            )
                            hideLoading()
                        } else if (response.body()?.status == "error") {
                            Toast.makeText(applicationContext,
                                "Login error",
                                Toast.LENGTH_LONG
                            ).show()
                            hideLoading()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Some error occurred try again",
                                Toast.LENGTH_LONG
                            ).show()
                            hideLoading()
                        }

                    }

                    override fun onFailure(call: Call<com.example.reburofirsttaskj.Response>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG)
                            .show()
                        hideLoading()

                    }


                })

        }

    }
}