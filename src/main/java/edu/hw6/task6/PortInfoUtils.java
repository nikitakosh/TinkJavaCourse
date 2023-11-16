package edu.hw6.task6;


import java.util.Map;
import java.util.Set;

public class PortInfoUtils {

    private PortInfoUtils() {}

    private static final Map<Integer, Port> KNOWN_PORTS = Map.ofEntries(
            Map.entry(48, new Port(Set.of(Protocol.UDP, Protocol.TCP), 48,
                    "AUDITD (Digital Audit Daemon)")),
            Map.entry(49, new Port(Set.of(Protocol.UDP, Protocol.TCP), 49,
                    "TACACS Login Host protocol")),
            Map.entry(50, new Port(Set.of(Protocol.UDP, Protocol.TCP), 50,
                    "RE-MAIL-CK")),
            Map.entry(64, new Port(Set.of(Protocol.UDP, Protocol.TCP), 64,
                    "COVIA")),
            Map.entry(66, new Port(Set.of(Protocol.UDP, Protocol.TCP), 66,
                    "SQL-NET")),
            Map.entry(270, new Port(Set.of(Protocol.UDP), 270,
                    "GIST (Q-mode encapsulation for GIST messages)")),
            Map.entry(271, new Port(Set.of(Protocol.TCP), 271,
                    "PT-TLS (IETF Network Endpoint Assessment/NEA Posture Transport Protocol over TLS)")),
            Map.entry(314, new Port(Set.of(Protocol.UDP, Protocol.TCP), 314,
                    "OPALIS-ROBOT")),
            Map.entry(315, new Port(Set.of(Protocol.UDP, Protocol.TCP), 315,
                    "DPSI")),
            Map.entry(316, new Port(Set.of(Protocol.UDP, Protocol.TCP), 316,
                    "DECAUTH")),
            Map.entry(21, new Port(Set.of(Protocol.TCP), 21,
                    "FTP — для передачи команд FTP")),
            Map.entry(135, new Port(Set.of(Protocol.TCP, Protocol.UDP), 135,
                    "LOC-SRV (Locator service)")),
            Map.entry(137, new Port(Set.of(Protocol.TCP, Protocol.UDP), 137,
                    "NETBIOS-NS (NetBIOS Name Service)")),
            Map.entry(5353, new Port(Set.of(Protocol.UDP), 5353,
                    "Multicast DNS (MDNS)")),
            Map.entry(5355, new Port(Set.of(Protocol.UDP, Protocol.TCP), 5355,
                    "LLMNR—Link-Local"))
    );

    public static String getPortInfo(int port, Protocol protocol) {
        Port currPort = KNOWN_PORTS.get(port);
        String info;
        if (currPort != null && currPort.protocol().contains(protocol)) {
            info = protocol + "\t" + port + "\t" + currPort.service();
        } else {
            info = protocol + "\t" + port + "\t" + "N/A";
        }
        return info;
    }

}
