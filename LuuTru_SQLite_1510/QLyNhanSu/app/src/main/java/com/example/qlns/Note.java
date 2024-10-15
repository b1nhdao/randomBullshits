package com.example.qlns;

public class Note {
    int id;
    String title, createDate, content;

    public Note(int id, String title, String createDate, String content) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.content = content;
    }

    public Note(String title, String createDate, String content) {
        this.title = title;
        this.createDate = createDate;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
