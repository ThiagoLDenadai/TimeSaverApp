package com.example.timsaverapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timsaverapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spAgendamentos = findViewById<Spinner>(R.id.spAgendamentos)

        val vetorDias = arrayListOf<Agendamentos>()

        val input_stream = baseContext.resources.openRawResource(R.raw.agendamentos)
        val buffer = input_stream.bufferedReader()

        val lines = buffer.readLines()
        val dias: MutableList<String> = ArrayList()

        for(item in lines){
            dias.add(item)
            val agendamento = Agendamentos(item)
            vetorDias.add(agendamento)
        }

        val aaAgendamento = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        spAgendamentos.adapter = aaAgendamento

        spAgendamentos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val tvUserItem = view as TextView

                var drawable_resource_name = tvUserItem.text.toString().lowercase().replace(" ", "_")

                val drawable_resouce_id = getResources().getIdentifier(drawable_resource_name,
                    "drawable", getPackageName())

                val adapter = ToDoAdapter(vetorDias[i].todoList)
                binding.rvTodos.adapter = adapter
                binding.rvTodos.layoutManager = LinearLayoutManager(getApplicationContext())

                binding.btnAddTodo.setOnClickListener {
                    val title = binding.etTodo.text.toString()
                    val todo = Todo(title, false)
                    vetorDias[i].todoList.add(todo)
                    adapter.notifyItemInserted(vetorDias[i].todoList.size - 1)
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }

    }
}