package com.pedro.todo.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedro.todo.Adapter.AdapterNote
import com.pedro.todo.Entitas.Note
import com.pedro.todo.Fragment.RoomFragment
import com.pedro.todo.R
import com.pedro.todo.ViewModel.NoteViewModel
import com.pedro.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


     lateinit var  model : NoteViewModel
    var adapter : AdapterNote = AdapterNote()

    companion object{
        val CREATE_NOTE_REQUET_KODE = 1
    }


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        setRecycler()

          adapter = AdapterNote()
//        model = ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        eventClick()
    }



    private fun setRecycler(){
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        lateinit var  model : NoteViewModel
        model.getListNote().observe(this , object : Observer<List<Note>>{
            override fun onChanged(t: List<Note>?) {
                if (t != null) {
                    adapter.setListNote(t)
                }

                binding.recycler.adapter = adapter
            }

        })
    }


    private fun eventClick(){
        binding.fabAdd.setOnClickListener{

            Intent(this@MainActivity , CreateNoteActivity::class.java).also {
                startActivityForResult(it , CREATE_NOTE_REQUET_KODE)

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NOTE_REQUET_KODE && resultCode == Activity.RESULT_OK){

            val note = data?.getStringExtra(CreateNoteActivity.New_Note)
//
//            if (note != null) {
//                model.insert(note)
//            }
            Toast.makeText(this , " Data ini berhasil di tambahakan" , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this , " Data ini gagal di tambahakan" , Toast.LENGTH_SHORT).show()

        }

    }





}