package com.example.pizzaapp3383

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SESUAIKAN DENGAN XML ANDA (ID dan Tipe View harus sama)
        val etNama = view.findViewById<EditText>(R.id.editTextName)
        val etEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val etPass = view.findViewById<EditText>(R.id.editTextPassword)
        val etLvl = view.findViewById<EditText>(R.id.editTextLevel)

        // Ambil data dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)

        val name = sharedPref.getString("key_name", "")
        val email = sharedPref.getString("key_email", "")
        val password = sharedPref.getString("key_password", "")
        val level = sharedPref.getString("key_level", "")

        // Tampilkan data ke EditText
        // Menggunakan setText() karena tipenya EditText
        etNama?.setText(name)
        etEmail?.setText(email)
        etPass?.setText(password)
        etLvl?.setText(level)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountFragment()
    }
}