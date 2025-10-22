package com.example.pizzaapp3383

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTrx = fragmentManager.beginTransaction()
        fragmentTrx.replace(R.id.fragmentContainerView, fragment)
        fragmentTrx.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // instance
        val txtAccount: TextView = findViewById(R.id.txtMenuAccount)
        val txtMenu: TextView = findViewById(R.id.txtMenuPizza)
        val txtTransaction: TextView = findViewById(R.id.txtMenuShopping)
        val txtReport: TextView = findViewById(R.id.txtMenuReport)
        val fragmentContainer: FragmentContainerView = findViewById(R.id.fragmentContainerView)

        txtAccount.setOnClickListener {
            replaceFragment(AccountFragment())
        }

        txtMenu.setOnClickListener {
            replaceFragment(MenuFragment())
        }

        txtTransaction.setOnClickListener {
            replaceFragment(TransactionFragment())
        }

        txtReport.setOnClickListener {
            replaceFragment(ReportFragment())
        }
    }
}