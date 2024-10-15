package com.example.qlns;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class InsertNote extends AppCompatActivity {

    EditText edt_title, edt_date, edt_content;
    Button btn_selectDate, btn_save;
    final Calendar calendar = Calendar.getInstance();
    dbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_note);
        getViews();

        dbManager = new dbManager(InsertNote.this);
        dbManager.open();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.insertNote(getNote());
                dbManager.close();
                Intent intent = new Intent(InsertNote.this, MainActivity2.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertNote.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        edt_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void getViews() {
        edt_title = findViewById(R.id.edt_title);
        edt_date = findViewById(R.id.edt_date);
        edt_content = findViewById(R.id.edt_content);
        btn_save = findViewById(R.id.btn_save);
        btn_selectDate = findViewById(R.id.btn_selectDate);
    }

    private Note getNote() {
        return new Note(
                edt_title.getText().toString(),
                edt_date.getText().toString(),
                edt_content.getText().toString()
        );
    }
}
