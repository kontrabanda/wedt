package localmax.models;


public class Phrase {
    public static Phrase of(String text, int startIndex, int endIndex) {
        Phrase phrase = new Phrase();

        phrase.text = text;
        phrase.startIndex = startIndex;
        phrase.endIndex = endIndex;

        return phrase;
    }

    public String text;
    public int startIndex;
    public int endIndex;
}
