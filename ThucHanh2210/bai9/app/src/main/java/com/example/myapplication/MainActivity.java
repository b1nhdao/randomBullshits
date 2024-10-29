package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_maLop, edt_tenLop, edt_siso;
    Button btn_them, btn_tim, btn_xoa, btn_sua;
    ListView listview;
    ArrayAdapter<Lop> adapter;
    ArrayList<Lop> dslop;
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getViews();

//        loadDBToListView();
//        createDatabase();
//        createTableLop(); // Ensure the table is created
        dslop = new ArrayList<>(); // Initialize the ArrayList

        createDatabase();
        addLop(new Lop("10A1", "Muoi A mot", 48));
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dslop);
        listview.setAdapter(adapter);
        loadDBToListView();

        // Set up button click listeners
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = new Lop(edt_maLop.getText().toString(), edt_tenLop.getText().toString(), Integer.parseInt(edt_siso.getText().toString()));
                dslop.add(lop);
                addLop(lop);
                loadDBToListView();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XoaLop(edt_maLop.getText().toString());
                loadDBToListView();
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = new Lop(edt_maLop.getText().toString(), edt_maLop.getText().toString(), Integer.parseInt(edt_siso.getText().toString()));
                UpdateLop(lop);
                loadDBToListView();
            }
        });

    }

    private void getViews() {
        edt_maLop = findViewById(R.id.edt_maLop);
        edt_tenLop = findViewById(R.id.edt_tenLop);
        edt_siso = findViewById(R.id.edt_siso);
        btn_them = findViewById(R.id.btn_them);
        btn_sua = findViewById(R.id.btn_sua);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_tim = findViewById(R.id.btn_tim);
        listview = findViewById(R.id.listview);
    }

    public void createDatabase() {
        db = openOrCreateDatabase("QLSV", MODE_PRIVATE, null);
    }

    public void closeDatabase() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public void selectDatabase(){
        String sql = "SELECT * FROM LOP";
        db.execSQL(sql);
    }

    public void deleteDatabase() {
        String thongbao;
        if (deleteDatabase("QLSV.db")) {
            thongbao = "Đã xóa thành công QLSV.db!!!";
        } else {
            thongbao = "Không thể xóa QLSV.db!";
        }
        Toast.makeText(MainActivity.this, thongbao, Toast.LENGTH_SHORT).show();
    }

    public void createTableLop() {
        String sql = "CREATE TABLE IF NOT EXISTS LOP(MaLop TEXT PRIMARY KEY, Tenlop TEXT, SiSo INTEGER)";
        db.execSQL(sql);
    }

    public void addLop(Lop lop) {
        ContentValues values = new ContentValues();
        try {
            values.put("MaLop", lop.getMalop());
            values.put("TenLop", lop.getTenLop());
            values.put("SiSo", lop.getSiSo());
            if (db.insert("Lop", null, values) == -1) {
                Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
            } else {
                dslop.add(lop); // Add the new Lop to the list
                adapter.notifyDataSetChanged(); // Update the adapter
                Toast.makeText(MainActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateLop(Lop lop) {
        ContentValues values = new ContentValues();
        values.put("TenLop", lop.getTenLop());
        values.put("SiSo", lop.getSiSo());
        int rowsAffected = db.update("Lop", values, "MaLop=?", new String[]{lop.getMalop()});
        if (rowsAffected > 0) {
            Toast.makeText(MainActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Không tìm thấy lớp!", Toast.LENGTH_SHORT).show();
        }
    }

    public void XoaLop(String maLop) {
        if (maLop == null || maLop.isEmpty()) {
            db.delete("Lop", null, null);
            dslop.clear(); // Clear the list if all are deleted
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Đã xóa tất cả lớp!", Toast.LENGTH_SHORT).show();
        } else {
            int rowsDeleted = db.delete("Lop", "MaLop=?", new String[]{maLop});
            if (rowsDeleted > 0) {
                Toast.makeText(MainActivity.this, "Đã xóa lớp có mã: " + maLop, Toast.LENGTH_SHORT).show();
                // Remove from the list if necessary
                for (Lop lop : dslop) {
                    if (lop.getMalop().equals(maLop)) {
                        dslop.remove(lop);
                        break;
                    }
                }
                adapter.notifyDataSetChanged(); // Update the adapter
            } else {
                Toast.makeText(MainActivity.this, "Không tìm thấy lớp có mã: " + maLop, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadDBToListView()
    {
        dslop.clear();
        Cursor c = db.query("Lop", null, null, null, null, null, null);
        c.moveToFirst();//chuyển về record đầu tiên
        String data = "";
        while(c.isAfterLast() == false)
        {
            dslop.add(new Lop(c.getString(0).toString(),
                    c.getString(1).toString(),
                    c.getInt(2)));
            c.moveToNext();
        }
//Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        c.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase(); // Ensure the database is closed when the activity is destroyed
    }
}
