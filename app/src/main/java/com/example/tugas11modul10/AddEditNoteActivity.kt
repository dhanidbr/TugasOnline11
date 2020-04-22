package com.example.tugas11modul10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.tugas11modul10.EXTRA_ID"
        const val EXTRA_JUDUL = "com.example.tugas11modul10.EXTRA_JUDUL"
        const val EXTRA_DESKRIPSI = "com.example.tugas11modul10.EXTRA_DESKRIPSI"
        const val EXTRA_NOMER = "com.example.tugas11modul10.EXTRA_NOMER"
        const val EXTRA_ALAMAT = "com.example.tugas11modul10.EXTRA_ALAMAT"
        const val EXTRA_PRIORITAS = "com.example.tugas11modul10.EXTRA_PRIORITAS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 5
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Catatan"
            edit_text_nama.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_npm.setText(intent.getStringExtra(EXTRA_DESKRIPSI))
            edit_text_nomor.setText(intent.getStringExtra(EXTRA_NOMER))
            edit_text_alamat.setText(intent.getStringExtra(EXTRA_ALAMAT))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        } else {
            title = "Tambah Catatan"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun saveNote() {
        if (edit_text_nama.text.toString().trim().isBlank() || edit_text_npm.text.toString().trim().isBlank()
            || edit_text_nomor.text.toString().trim().isBlank() || edit_text_alamat.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_nama.text.toString())
            putExtra(EXTRA_DESKRIPSI, edit_text_npm.text.toString())
            putExtra(EXTRA_NOMER, edit_text_nomor.text.toString())
            putExtra(EXTRA_ALAMAT, edit_text_alamat.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}