package com.example.pizzaapp3383

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pizzaapp3383.client.RetrofitClient
import com.example.pizzaapp3383.response.account.AccountResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNama = view.findViewById<EditText>(R.id.editTextName)
        val etEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val etPass = view.findViewById<EditText>(R.id.editTextPassword)
        val etLvl = view.findViewById<EditText>(R.id.editTextLevel)
        val btnSave = view.findViewById<Button>(R.id.buttonNext)

        val sharedPref = requireActivity().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)

        // Load data dari SharedPreferences
        etNama.setText(sharedPref.getString("key_name", ""))
        etEmail.setText(sharedPref.getString("key_username", ""))
        etPass.setText(sharedPref.getString("key_password", ""))
        etLvl.setText(sharedPref.getString("key_level", ""))

        btnSave.setOnClickListener {
            val user = etEmail.text.toString().trim()
            val nama = etNama.text.toString().trim()
            val level = etLvl.text.toString().trim()
            val pass = etPass.text.toString().trim()

            Log.d("API_DEBUG", "Mengirim: $user, $nama, $level")

            RetrofitClient.instance.putAccount(user, nama, level, pass)
                .enqueue(object : Callback<AccountResponse> {
                    override fun onResponse(call: Call<AccountResponse>, response: Response<AccountResponse>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            // Jika server sukses update (biasanya body?.success == true)
                            Toast.makeText(context, "Update Berhasil di Database", Toast.LENGTH_SHORT).show()

                            // Update SharedPreferences agar data sinkron
                            sharedPref.edit().apply {
                                putString("key_username", user)
                                putString("key_name", nama)
                                putString("key_level", level)
                                putString("key_password", pass)
                                apply()
                            }
                        } else {
                            // Jika response.code bukan 200 (misal 404 atau 500)
                            Log.e("API_DEBUG", "Error Code: ${response.code()}")
                            Toast.makeText(context, "Server Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                        // Ini yang muncul di logcat Anda (Malformed JSON)
                        Log.e("API_DEBUG", "Respon server bukan JSON: ${t.message}")
                        Toast.makeText(context, "Gagal: Server mengirim respon tidak valid", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}