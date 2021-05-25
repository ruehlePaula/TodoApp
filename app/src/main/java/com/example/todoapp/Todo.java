package com.example.todoapp;


import android.os.Bundle;
import java.util.ArrayList;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todoapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class Todo extends AppCompatActivity {
    private ArrayList <String> items;
    private ArrayAdapter <String> itemsAdapter;
    private ListView List;
    private EditText EditText;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_todo);

        List=(ListView) findViewById(R.id.list);
        items =new ArrayList<String>();
        itemsAdapter=new ArrayAdapter<String>(Todo.this, android.R.layout.simple_list_item_1,items);
        List.setAdapter(itemsAdapter);

        EditText=(TextInputEditText)findViewById(R.id.todo_input);
        EditText.setOnEditorActionListener((view,arg1,arg2)->{
            if(arg1== EditorInfo.IME_ACTION_DONE){
                String itemText=EditText.getText().toString();
                itemsAdapter.add(itemText);
                EditText.setText("");
                return false;
            }
            return false;
        });


    }
}
