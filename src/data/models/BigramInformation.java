package data.models;


public class BigramInformation {
    public String firstWord;
    public String secondWord;
    public double frequency;

    public static BigramInformation of(String firstWord, String secondWord, double frequency) {
        return new BigramInformation(firstWord, secondWord, frequency);
    }

    public BigramInformation() {}

    public BigramInformation(String firstWord, String secondWord, double frequency) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
        this.frequency = frequency;
    }
}
