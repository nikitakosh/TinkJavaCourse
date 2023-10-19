package edu.hw1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task1Test {
    @Test
    @DisplayName("тест на null")
    public void minutesToSecondsTestInputNull(){
        Assertions.assertThatThrownBy(() -> Task1.minutesToSeconds(null)).isInstanceOf(NullPointerException.class);
    }

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

    @ParameterizedTest
    @MethodSource("provideInputData")
    @DisplayName("ввод, когда в минутах больше двух разрядов")
    public void minutesToSecondsTestMoreThanTwoDigits(String time, int expected){
        Assertions.assertThat(Task1.minutesToSeconds(time)).isEqualTo(expected);
    }
    static Stream<Arguments> provideInputData() {
        return Stream.of(
            Arguments.of("100:20", 6020),
            Arguments.of("1000:20", 60020),
            Arguments.of("3050:20", 183020)
        );
    }
}
