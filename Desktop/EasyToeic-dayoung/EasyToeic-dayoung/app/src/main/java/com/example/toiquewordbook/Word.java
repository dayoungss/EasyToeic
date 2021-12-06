package com.example.toiquewordbook;

public class Word {

    int _id;
    String eng;
    String kor;
    String engpron;
    String sentence;


    public Word(int _id, String eng, String engpron, String kor, String sentence) {
        this._id = _id;
        this.eng = eng;
        this.kor = kor;
        this.engpron = engpron;
        this.sentence = sentence;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getKor() {
        return kor;
    }

    public void setKor(String kor) {
        this.kor = kor;
    }

    public String getEngpron() {
        return engpron;
    }

    public void setEngpron(String engpron) {
        this.engpron = engpron;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
