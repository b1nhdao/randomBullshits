package com.example.qlns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    private Context context;
    private int layout;
    ArrayList<Note> listNotes;

    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listNotes = objects;
    }

    @Nullable
    @Override
    public Note getItem(int position) {
        return listNotes.get(position);
    }

    @Override
    public int getCount() {
        return listNotes.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null){
            //load layout note
            currentView = LayoutInflater.from(context).inflate(layout, parent, false);
        }
        Note note = getItem(position);
        TextView tv_title = currentView.findViewById(R.id.tv_title);
        TextView tv_date = currentView.findViewById(R.id.tv_date);
        tv_title.setText(note.getTitle());
        tv_date.setText(note.getCreateDate());
        return currentView;
    }
}
