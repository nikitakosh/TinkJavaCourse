package edu.project1;

import edu.project1.exceptions.InvalidWordGivenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ConsoleHangmanTest {

    @Test
    @DisplayName("empty hidden word test")
    public void emptyWordTest(){
        Dictionary dictionary = new HangmanDictionary(List.of(""));
        ConsoleHangman consoleHangman = new ConsoleHangman();
        Assertions.assertThrows(InvalidWordGivenException.class, ()->consoleHangman.run(dictionary));
    }
}
