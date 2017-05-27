package data.models;


public class Bigram {
    public String firstWord;
    public String secondWord;

    public static Bigram of(String firstWord, String secondWord) {
        return new Bigram(firstWord, secondWord);
    }

    public Bigram() {}

    public Bigram(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }
}
