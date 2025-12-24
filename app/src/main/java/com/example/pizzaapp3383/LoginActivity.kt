package com.example.pizzaapp3383

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pizzaapp3383.client.RetrofitClient
import com.example.pizzaapp3383.response.account.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtUsername: EditText = findViewById(R.id.editTextUsername)
        val txtPassword: EditText = findViewById(R.id.editTextPassword)
        val btnLogin: Button = findViewById(R.id.buttonNext)

        btnLogin.setOnClickListener {
            var user = txtUsername.text.toString().trim()
            var pwd = txtPassword.text.toString().trim()

            if (user.isEmpty()) {
                txtUsername.error = "Email required"
                txtUsername.requestFocus()
                return@setOnClickListener
            }

            if (pwd.isEmpty()) {
                txtUsername.error = "Password required"
                txtUsername.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.postLogin(user, pwd).enqueue(
                object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val account = response.body()
                        if (account?.success == true) {
                            Toast.makeText(this@LoginActivity,
                                account?.message.toString(), Toast.LENGTH_SHORT).show()
                            val intentLogin = Intent(this@LoginActivity,
                                HomeActivity::class.java)
                            startActivity(intentLogin)
                        } else {
                            Toast.makeText(this@LoginActivity,
                                account?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                }
            )

        }
    }
}