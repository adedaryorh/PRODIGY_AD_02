package com.example.todolistapp;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button butnAdd, butnEdit, butnDelete;
    private ListView listViewTasks;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        butnAdd = findViewById(R.id.buttonAdd);
        butnEdit = findViewById(R.id.buttonEdit);
        butnDelete = findViewById(R.id.buttonDelete);
        listViewTasks = findViewById(R.id.listViewTasks);

        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, taskList);
        listViewTasks.setAdapter(taskAdapter);
        listViewTasks.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        butnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Kindly enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    String task = editTextTask.getText().toString().trim();
                    if (!task.isEmpty()) {
                        taskList.set(selectedPosition, task);
                        taskAdapter.notifyDataSetChanged();
                        editTextTask.setText("");
                        listViewTasks.clearChoices();
                        selectedPosition = -1;
                    } else {
                        Toast.makeText(MainActivity.this, "Kindly input a task ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Kindly choose a task to edit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    taskList.remove(selectedPosition);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                    listViewTasks.clearChoices();
                    selectedPosition = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Kindly select a task to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                editTextTask.setText(taskList.get(position));
            }
        });
    }
}