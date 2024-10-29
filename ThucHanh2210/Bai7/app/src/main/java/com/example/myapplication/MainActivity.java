package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    EditText edt_username, edt_password;
    CheckBox cb_remember;
    Button btn_dangnhap;
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

        getViews();
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savingPreferences();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name", edt_username.getText().toString());
                startActivity(intent);
            }
        });

    }
    private void getViews(){
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        cb_remember = findViewById(R.id.cb_remember);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
    }

    private void savingPreferences(){
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre = this.getSharedPreferences(fName,
                Context.MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        //Lưu trữ dữ liệu dạng key/value
        String user = edt_username.getText().toString();
        String pwd = edt_password.getText().toString();
        boolean bchk = cb_remember.isChecked();
        if(!bchk)
        {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        }
        else
        {
            //lưu vào editor
            editor.putString("user", user);
            editor.putString("pwd", pwd);
            editor.putBoolean("checked", bchk);
        }
        editor.commit();
    }


    private void restoringPreferences() {
        SharedPreferences pre = this.getSharedPreferences(fName,
                Context.MODE_PRIVATE);
        if(pre != null) {
            boolean bchk = pre.getBoolean("checked", false);
            if (bchk) {
                String user = pre.getString("user", "admin");
                String pwd = pre.getString("pwd", "123");
                edt_username.setText(user);
                edt_password.setText(pwd);
            }
            cb_remember.setChecked(bchk);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        savingPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringPreferences();
    }

}