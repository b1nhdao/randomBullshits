package com.example.qlns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    FloatingActionButton btn_insertNote;
    ListView listView;
    NoteAdapter adapter;
    ArrayList<Note> listNotes = new ArrayList<>();
    dbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Gọi getViews để ánh xạ các thành phần giao diện
        getViews();

        // Thiết lập padding cho view chính
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Đọc dữ liệu và hiển thị lên ListView
        dbManager = new dbManager(MainActivity2.this);
        dbManager.open();
        listNotes = dbManager.getAllNotes();

        // Đảm bảo rằng adapter khớp với layout của từng item, không phải activity_main2
        adapter = new NoteAdapter(MainActivity2.this, R.layout.layout_note, listNotes);
        listView.setAdapter(adapter); // Gán adapter cho ListView
        adapter.notifyDataSetChanged();

        // Xử lý sự kiện cho nút thêm ghi chú
        btn_insertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, InsertNote.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện khi click vào từng item trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = listNotes.get(i).getId();
                Intent intent = new Intent(MainActivity2.this, UpdateNote.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    // Ánh xạ các thành phần giao diện
    private void getViews(){
        btn_insertNote = findViewById(R.id.btn_insertNote);
        listView = findViewById(R.id.lv_note);
    }
}
