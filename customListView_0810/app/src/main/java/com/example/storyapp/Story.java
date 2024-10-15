package com.example.storyapp;

import java.io.Serializable;

public class Story implements Serializable {
    int image;
    String title, author, content;

    public Story(int image, String title, String author, String content) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
