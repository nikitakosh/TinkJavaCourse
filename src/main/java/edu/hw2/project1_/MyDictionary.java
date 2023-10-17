package edu.hw2.project1_;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDictionary implements Dictionary {
    private final List<String> words;

    public MyDictionary(List<String> words) {
        this.words = words;
    }
    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}
