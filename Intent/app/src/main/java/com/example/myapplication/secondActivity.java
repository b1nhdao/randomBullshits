package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class secondActivity extends AppCompatActivity {

    Button btn_lam, btn_dong;
    EditText edt_a , edt_b;
    TextView tv_kq, tv_phepToan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt_a = findViewById(R.id.edt_a);
        edt_b = findViewById(R.id.edt_b);
        btn_lam = findViewById(R.id.btn_Lam);
        btn_dong = findViewById(R.id.btn_dong);
        tv_kq = findViewById(R.id.tv_kq);
        tv_phepToan = findViewById(R.id.tv_phepToan);

        Intent intent = getIntent();
        String phepToan = intent.getStringExtra("pheptoan");
        tv_phepToan.setText("Phep toan " + phepToan);

//        Toast.makeText(this, phepToan, Toast.LENGTH_SHORT).show();

        btn_lam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float kq = tinhToan(phepToan, Float.parseFloat(edt_a.getText().toString()), Float.parseFloat(edt_b.getText().toString()));
                tv_kq.setText(tv_kq.getText().toString() + ": " + kq);
            }
        });

        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private float tinhToan(String s, float a, float b){
        float kq;
        switch (s){
            case "+":
                kq = a + b;
                break;
            case "-":
                kq = a - b;
                break;
            case "x":
                kq = a * b;
                break;
            case "/":
                kq = a / b;
                break;
            default:
                return 0;
        }
        return kq;
    }

}