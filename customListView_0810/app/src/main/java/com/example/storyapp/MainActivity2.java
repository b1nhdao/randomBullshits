package com.example.storyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class MainActivity2 extends AppCompatActivity implements Serializable {

    EditText edt_title, edt_author, edt_content;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Kích hoạt Edge-to-Edge
        setContentView(R.layout.activity_main2);

        // Xử lý insets cho chế độ toàn màn hình
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt_author = findViewById(R.id.edt_author);
        edt_content = findViewById(R.id.edt_context);  // Sửa id từ edt_context thành edt_content
        edt_title = findViewById(R.id.edt_title);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Trả về đối tượng story để thêm vào list ở MainActivity
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("story", getStory());
                intent.putExtras(bundle);  // Sửa từ putExtra sang putExtras
                setResult(RESULT_OK, intent);
                finish();  // Đóng activity sau khi thêm story
            }
        });
    }

    // Hàm tạo đối tượng Story từ dữ liệu nhập
    private Story getStory() {
        String title = edt_title.getText().toString();
        String author = edt_author.getText().toString();
        String content = edt_content.getText().toString();  // Sửa lấy content đúng cách
        int id = R.drawable.ic_launcher_foreground;  // Dùng hình mặc định cho câu chuyện
        return new Story(id, title, author, content);
    }
}
