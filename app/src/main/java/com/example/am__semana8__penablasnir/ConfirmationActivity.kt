package com.example.am__semana8__penablasnir

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.am__semana8__penablasnir.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnCerrar.setOnClickListener {
            finishAffinity()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAffinity()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
