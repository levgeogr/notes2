package ru.note.entities;

public class Note {
    private int id;

    private String title;

    private String textbody;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextbody() {
        return textbody;
    }

    public void setTextbody(String textbody) {
        this.textbody = textbody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note (int id, String title, String textbody) {
        this.id = id;
        this.title = title;
        this.textbody = textbody;
    }

    public Note(int id){
        this.id = id;
    }
}
