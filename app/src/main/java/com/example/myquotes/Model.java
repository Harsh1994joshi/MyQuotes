package com.example.myquotes;

public class Model {

    String  Id,Quotes,Author;

    public Model() {
    }

    public Model(String id, String quotes, String author) {
        Id = id;
        Quotes = quotes;
        Author = author;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getQuotes() {
        return Quotes;
    }

    public void setQuotes(String quotes) {
        Quotes = quotes;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
