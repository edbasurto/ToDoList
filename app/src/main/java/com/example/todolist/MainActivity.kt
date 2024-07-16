package com.example.todolist

import android.R
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityMainBinding
import kotlinx.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        /**
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        **/

        val taskList = arrayListOf<String>()

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, taskList)

        // var arrayAdapter = ArrayAdapter(context = this, android.R.layout.simple_list_item_multiple_choice, taskList)

        binding.addButton.setOnClickListener {
            taskList.add(binding.newTaskEntry.text.toString())

            binding.toDoListView.adapter = arrayAdapter

            arrayAdapter.notifyDataSetChanged()

            binding.newTaskEntry.text.clear()
        }

        binding.deleteButton.setOnClickListener {
            val positionChecked: SparseBooleanArray = binding.toDoListView.checkedItemPositions

            var deleteItem = binding.toDoListView.count - 1

            while(deleteItem >= 0) {
                if(positionChecked.get(deleteItem)) {
                    arrayAdapter.remove(taskList.get(deleteItem))
                }
                deleteItem--
            }

            positionChecked.clear()
            arrayAdapter.notifyDataSetChanged()
        }
    }
}