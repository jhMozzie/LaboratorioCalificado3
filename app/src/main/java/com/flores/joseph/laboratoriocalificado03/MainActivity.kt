package com.flores.joseph.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.flores.joseph.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var listProfesor: List<ProfesorResponse> = emptyList()
    private val adapter by lazy {ProfesorAdapter(listProfesor)}
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()

    }

    private fun setupRecyclerView(){
        binding.rvProfesor.adapter = adapter
    }


    private fun observeViewModel() {
        viewModel.listProfesor.observe(this, Observer { profesores ->
            profesores?.let {
                adapter.List = it
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.errorApi.observe(this, Observer { error ->
            error?.let {
                if (it.isNotEmpty()) {
                    showMessage(it)
                }
            }
        })
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}