package localmax;


import java.util.ArrayList;
import java.util.List;

public class EntityFilter {
    private List<String> allEntites;

    EntityFilter(List<String> entities) {
        allEntites = entities;
    }

    List<String> getEntitiesFilteredByCapitalLetters() {
        List<String> result = new ArrayList<>();

        for(String singleEntity: allEntites) {
            if(checkIfNamedEntity(singleEntity)) {
                result.add(singleEntity);
            }
        }

        return result;
    }

    private boolean checkIfNamedEntity(String entity) {
        String[] words = entity.split("\\W+");
        int wordsCount = words.length;
        int capitalizedWordsCount = getCapitalizedWordsCount(words);

        return (double)capitalizedWordsCount/wordsCount > 0.5;
    }

    private int getCapitalizedWordsCount(String[] words) {
        int result = 0;

        for(String singleWord: words) {
            result += checkIfCapitalized(singleWord) ? 1 : 0;
        }

        return result;
    }

    private boolean checkIfCapitalized(String word) {
        return Character.isUpperCase(word.charAt(0));
    }
}
