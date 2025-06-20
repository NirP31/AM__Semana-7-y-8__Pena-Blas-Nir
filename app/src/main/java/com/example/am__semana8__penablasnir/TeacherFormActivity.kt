package com.example.am__semana8__penablasnir

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.am__semana8__penablasnir.databinding.ActivityTeacherFormBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class TeacherFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherFormBinding
    private var selectedAsistencia: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tvInfo.paintFlags = binding.tvInfo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvInfo.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }
        binding.cardSelectAsistencia.setOnClickListener {
            showAsistenciaDialog()
        }
        binding.btnRegistrar.setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("role", "Docente")
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
    private fun showAsistenciaDialog() {
        val options = resources.getStringArray(R.array.asistencia_options)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title_select_asistencia))
            .setItems(options) { dialog, which ->
                selectedAsistencia = options[which]
                showAsistenciaChip(selectedAsistencia!!)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.dialog_cancelar)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    private fun showAsistenciaChip(text: String) {
        binding.containerAsistencia.removeAllViews()
        val chip = Chip(this).apply {
            this.text = text
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                binding.containerAsistencia.removeAllViews()
                binding.containerAsistencia.addView(binding.cardSelectAsistencia)
                selectedAsistencia = null
            }
        }
        val chipGroup = ChipGroup(this).apply {
            layoutParams = binding.cardSelectAsistencia.layoutParams
            addView(chip)
        }
        binding.containerAsistencia.addView(chipGroup)
    }
    private fun validateInputs(): Boolean {
        var ok = true
        val apellidos = binding.etApellidos.text.toString().trim()
        val nombre = binding.etNombre.text.toString().trim()
        val dni = binding.etDNI.text.toString().trim()
        val correo = binding.etCorreo.text.toString().trim()
        if (apellidos.isEmpty()) {
            binding.tilApellidos.error = getString(R.string.error_ingrese_apellidos)
            ok = false
        } else binding.tilApellidos.error = null
        if (nombre.isEmpty()) {
            binding.tilNombre.error = getString(R.string.error_ingrese_nombre)
            ok = false
        } else binding.tilNombre.error = null
        if (dni.isEmpty() || dni.length != 8 || !dni.all { it.isDigit() }) {
            binding.tilDNI.error = getString(R.string.error_dni_invalido)
            ok = false
        } else binding.tilDNI.error = null
        if (selectedAsistencia.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.error_seleccione_asistencia), Toast.LENGTH_SHORT).show()
            ok = false
        }
        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.tilCorreo.error = getString(R.string.error_correo_invalido)
            ok = false
        } else binding.tilCorreo.error = null
        if (!ok) {
            Toast.makeText(this, getString(R.string.toast_corrija_errores), Toast.LENGTH_SHORT).show()
        }
        return ok
    }
}
