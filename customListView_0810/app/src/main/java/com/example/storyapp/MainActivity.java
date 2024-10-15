package com.example.storyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

FloatingActionButton btn_add;
ListView listView;
ArrayList<Story> storyList = new ArrayList<>();
StoryAdapter adapter;

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
        btn_add = findViewById(R.id.btn_add);
        listView = findViewById(R.id.listview);
        initData();
        adapter = new StoryAdapter(MainActivity.this, R.layout.story_item_layout, storyList);
        listView.setAdapter(adapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                callMainActivity2.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> callMainActivity2 =
    registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == RESULT_OK){
                Intent intent = o.getData();
                Story story = (Story) intent.getExtras().get("story");

                storyList.add(story);
                adapter.notifyDataSetChanged();

            }
        }
    });

    private void initData(){
        storyList.add(new Story(R.drawable.ic_launcher_foreground, "title1", "author1", "cocntext1"));
        storyList.add(new Story(R.drawable.ic_launcher_foreground, "title2", "author2", "cocntext2"));
        storyList.add(new Story(R.drawable.ic_launcher_foreground, "title3", "author3", "cocntext3"));
        storyList.add(new Story(R.drawable.ic_launcher_foreground, "title4", "author4", "cocntext4"));

    }
}