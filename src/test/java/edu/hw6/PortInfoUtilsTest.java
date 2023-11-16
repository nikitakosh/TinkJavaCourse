package edu.hw6;

import edu.hw6.task6.PortInfoUtils;
import edu.hw6.task6.Protocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortInfoUtilsTest {


    @Test
    @DisplayName("port info utils")
    public void portInfoUtils() {
        Assertions.assertEquals(PortInfoUtils.getPortInfo(48, Protocol.UDP), "UDP\t48\tAUDITD (Digital Audit Daemon)");
        Assertions.assertEquals(PortInfoUtils.getPortInfo(5355, Protocol.TCP), "TCP\t5355\tLLMNR—Link-Local");
        Assertions.assertEquals(PortInfoUtils.getPortInfo(5300, Protocol.TCP), "TCP\t5300\tN/A");
    }
}
