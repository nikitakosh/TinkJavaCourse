package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.CallingInfoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {


    @Test
    @DisplayName("test input valid depth")
    public void callingInfoInputValidDepthTest(){
        Assertions.assertEquals(CallingInfoUtils.callingInfo(), new CallingInfo("edu.hw2.Task4Test", "callingInfoInputValidDepthTest"));
    }
}
