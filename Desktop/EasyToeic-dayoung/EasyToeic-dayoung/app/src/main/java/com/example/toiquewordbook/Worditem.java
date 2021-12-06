package com.example.toiquewordbook;

public class Worditem {
    private String word;
    private String word_mean;
    // private int check;
    //  private int myword;

    public Worditem(String word, String word_mean) {
        this.word = word;
        this.word_mean = word_mean;
        //   this.check = check;
        //   this.myword = myword;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_mean() {
        return word_mean;
    }

    public void setWord_mean(String word_mean) {
        this.word_mean = word_mean;
    }
/*
    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getMyword() {
        return myword;
    }

    public void setMyword(int myword) {
        this.myword = myword;
    }

 */
}