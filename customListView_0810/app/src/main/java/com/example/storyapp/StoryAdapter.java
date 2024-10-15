package com.example.storyapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StoryAdapter extends ArrayAdapter<Story> {
    Context context;  // Sửa kiểu từ Activity sang Context
    int layoutId;
    ArrayList<Story> listStory;

    public StoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Story> objects) {
        super(context, resource, objects);
        this.context = context;  // Sửa lại để lưu context chính xác
        this.layoutId = resource;
        this.listStory = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Kiểm tra và tái sử dụng convertView để tối ưu hiệu suất
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);  // Sửa lại để lấy inflater đúng cách
            convertView = inflater.inflate(layoutId, parent, false);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_author = convertView.findViewById(R.id.tv_author);
        ImageButton btn_read = convertView.findViewById(R.id.btn_read);
        ImageView img = convertView.findViewById(R.id.imageView);

        // Lấy đối tượng Story tại vị trí tương ứng
        Story story = listStory.get(position);
        tv_title.setText(story.getTitle());
        tv_author.setText(story.getAuthor());
        img.setImageResource(story.getImage());

        // Xử lý sự kiện khi click vào nút "Đọc"
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "reading", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return listStory.size();
    }

    @Nullable
    @Override
    public Story getItem(int position) {
        return listStory.get(position);
    }
}
