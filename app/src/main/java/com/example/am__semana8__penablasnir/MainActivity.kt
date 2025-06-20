package com.example.am__semana8__penablasnir

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.am__semana8__penablasnir.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAlumno.setOnClickListener {
            startActivity(Intent(this, StudentFormActivity::class.java))
        }
        binding.btnDocente.setOnClickListener {
            startActivity(Intent(this, TeacherFormActivity::class.java))
        }
    }
}
