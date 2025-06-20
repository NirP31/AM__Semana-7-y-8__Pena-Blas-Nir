package com.example.am__semana8__penablasnir

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.am__semana8__penablasnir.databinding.ActivityStudentFormBinding

class StudentFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tvInfo.paintFlags = binding.tvInfo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvInfo.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }
        binding.btnRegistrar.setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("role", "Alumno")
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun validateInputs(): Boolean {
        var ok = true
        val apellidos = binding.etApellidos.text.toString().trim()
        val nombre = binding.etNombre.text.toString().trim()
        val codigo = binding.etCodigo.text.toString().trim()
        val correo = binding.etCorreo.text.toString().trim()
        val dni = binding.etDNI.text.toString().trim()
        if (apellidos.isEmpty()) {
            binding.tilApellidos.error = getString(R.string.error_ingrese_apellidos)
            ok = false
        } else {
            binding.tilApellidos.error = null
        }
        if (nombre.isEmpty()) {
            binding.tilNombre.error = getString(R.string.error_ingrese_nombre)
            ok = false
        } else {
            binding.tilNombre.error = null
        }
        if (codigo.isEmpty()) {
            binding.tilCodigo.error = getString(R.string.error_ingrese_codigo)
            ok = false
        } else {
            binding.tilCodigo.error = null
        }
        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.tilCorreo.error = getString(R.string.error_correo_invalido)
            ok = false
        } else {
            binding.tilCorreo.error = null
        }
        if (dni.isEmpty() || dni.length != 8 || !dni.all { it.isDigit() }) {
            binding.tilDNI.error = getString(R.string.error_dni_invalido)
            ok = false
        } else {
            binding.tilDNI.error = null
        }
        if (!ok) {
            Toast.makeText(this, getString(R.string.toast_corrija_errores), Toast.LENGTH_SHORT).show()
        }
        return ok
    }
}
