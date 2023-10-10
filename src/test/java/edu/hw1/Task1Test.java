package edu.hw1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("ввод целого количества минут")
    public void minutesToSecondsTestInputOnlyMinutes(){
        Assertions.assertThat(Task1.minutesToSeconds("01:00")).isEqualTo(60);
    }
    @Test
    @DisplayName("ввод минут и секунд")
    public void minutesToSecondsTestInputMinutesAndSeconds(){
        Assertions.assertThat(Task1.minutesToSeconds("13:56")).isEqualTo(836);
    }
    @Test
    @DisplayName("ввод некорретного количества секунд")
    public void minutesToSecondsTestInputOverSeconds(){
        Assertions.assertThat(Task1.minutesToSeconds("10:60")).isEqualTo(-1);
    }

}
