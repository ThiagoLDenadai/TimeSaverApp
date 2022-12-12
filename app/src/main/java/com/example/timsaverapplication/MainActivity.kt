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

        val spDogData = findViewById<Spinner>(R.id.spDogData)
        val tvDogHeight = findViewById<TextView>(R.id.tvDogHeight)
        val tvDogColor = findViewById<TextView>(R.id.tvDogColor)
        val tvDogChar = findViewById<TextView>(R.id.tvDogChar)

        val breeds = arrayListOf<Dog>()  //bunch of dog objects

        val input_stream = baseContext.resources.openRawResource(R.raw.dog_data)
        // create a BufferedeReader from the input stream in order to read line-by-line
        val buffer = input_stream.bufferedReader()

        val lines = buffer.readLines() // creates a List of strings
        val dogNames: MutableList<String> = ArrayList()

        // instantiate Dog objects and add to ArrayList
        // also create list of breed names to use with spinner
        for(item in lines){
            val tokens = item.split("/")  //break into tokens based on delimiter
            dogNames.add(tokens[0]) // add names to list to use to populate spinner
            val aDog = Dog(tokens[0], tokens[1].toInt(), tokens[2], tokens[3] ) //instantiate a dog
            breeds.add(aDog)  // add dog to the arraylist of dog objects
        }

        //use array of string to populate a spinner
        val aaDogData = ArrayAdapter(this, android.R.layout.simple_spinner_item, dogNames)
        //aaDogData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // The drop down view
        spDogData.adapter = aaDogData

        // when item chosen in spinner display same thing in textview
        spDogData.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val tvUserItem = view as TextView
                Toast.makeText(applicationContext, "You chose " + tvUserItem.text.toString(), Toast.LENGTH_SHORT).show()

                var drawable_resource_name = tvUserItem.text.toString().lowercase().replace(" ", "_")

                //get the id from the resource name
                val drawable_resouce_id = getResources().getIdentifier(drawable_resource_name,
                    "drawable", getPackageName())

                tvDogHeight.text = "Average height: " + breeds[i].ave_height_inches +" inches"
                tvDogColor.text = "Colors: " + breeds[i].colors
                tvDogChar.text = "Characteristics: " + breeds[i].character
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }//end onItemSelectedListener

        var todoList = mutableListOf(
            Todo("Nenhuma Tarefa", false)
        )

        val adapter = ToDoAdapter(todoList)
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val title = binding.etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size - 1)
        }

    }//end onCreate
}//end MainActivity class