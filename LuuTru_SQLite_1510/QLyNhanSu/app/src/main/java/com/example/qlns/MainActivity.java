package com.example.qlns;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_dangnhap, btn_dong;
    EditText edt_username, edt_password;
    CheckBox cb_save;
    public static final String fName = "account.xml";
    SharedPreferences preferences;

    //dinh nghia int mode:
    public static final int MODE = Activity.MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //lay ra doi tuong sharedPrefernces de thuc hien doc ghi du lieu vao file
        preferences = getSharedPreferences(fName, MODE);

        //databind
        readAccount();

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAccount();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void getViews(){
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_dong = findViewById(R.id.btn_dong);
        edt_password = findViewById(R.id.edt_password);
        edt_username = findViewById(R.id.edt_user);
        cb_save = findViewById(R.id.cb_save);
    }

    private void saveAccount(){
        //luu dl vao file
        //lay ra doi tuong Editor de luu du lieu vao file
        if(cb_save.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();

            //put du lieu vao file xml
            editor.putBoolean("save", cb_save.isChecked());
            editor.putString("username", edt_username.getText().toString());
            editor.putString("password", edt_password.getText().toString());
            //hoan thanh viec luu du lieu
            editor.commit();
        }
    }

    private void readAccount(){
        boolean isSave = preferences.getBoolean("save", false);
        if(isSave){
            //doc tiep du lieu va data bind vao views (cac edittext)
            String username = preferences.getString("username", null);
            String password = preferences.getString("password", null);
            edt_username.setText(username);
            edt_password.setText(password);
        }
    }
}