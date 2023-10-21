package edu.project1;


import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class HangmanDictionary implements Dictionary {
    private final List<String> words;

    public HangmanDictionary(List<String> words) {
        this.words = words;
    }

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}
