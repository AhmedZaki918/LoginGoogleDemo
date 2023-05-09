package com.example.logintest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class HomeActivity : AppCompatActivity() {

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var btnLogout: Button
    private lateinit var tvEmail: TextView
    private lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        getUserInfo()
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun initViews() {
        gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        gsc = GoogleSignIn.getClient(this, gso)
        btnLogout = findViewById(R.id.btn_logout)
        tvName = findViewById(R.id.tv_name)
        tvEmail = findViewById(R.id.tv_email)
    }

    private fun getUserInfo() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            tvName.text = account.displayName
            tvEmail.text = account.email
        }
    }

    private fun logout() {
        gsc.signOut().addOnSuccessListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}