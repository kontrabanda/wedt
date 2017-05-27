package data.models;


public class WordInformation {
    public String word;
    public double frequency;

    public static WordInformation of(String word, double frequency) {
        return new WordInformation(word, frequency);
    }

    WordInformation() {}

    WordInformation(String word, double frequency) {
        this.word = word;
        this.frequency = frequency;
    }
}
