package com.myapplication.data.model;

public class Word
{
    private String word_id,word,category,user_id;

    public Word(String word, String category,String user_id)
    {
        this.word = word;
        this.category = category;
        this.user_id = user_id;
    }
    public Word(String word,String user_id)
    {
        this.word = word;
        this.user_id = user_id;
    }


    public String getWord_id()
    {
        return word_id;
    }

    public void setWord_id(String word_id) {
        this.word_id = word_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Word() {
    }

    public Word(String word_id, String word, String category, String user_id)
    {
        this.word_id = word_id;
        this.word = word;
        this.category = category;
        this.user_id = user_id;
    }
}
