package com.example.pizzaapp3383

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

        val txtUsername: EditText = findViewById(R.id.editTextUsername)
        val txtPassword: EditText = findViewById(R.id.editTextPassword)
        val btnLogin: Button = findViewById(R.id.buttonNext)

        btnLogin.setOnClickListener {
            val user = txtUsername.text.toString().trim()
            val pwd = txtPassword.text.toString().trim()

            if (user.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.postLogin(user, pwd).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginBody = response.body()
                    if (response.isSuccessful && loginBody?.success == true) {

                        // Simpan data ke SharedPreferences
                        val sharedPref = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
                        sharedPref.edit().apply {
                            putString("key_username", loginBody.data.username)
                            putString("key_name", loginBody.data.name)
                            putString("key_level", loginBody.data.level)
                            putString("key_password", loginBody.data.password)
                            apply()
                        }

                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, loginBody?.message ?: "Login Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Koneksi Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}