package edu.hw6.task6;

import java.util.Set;

public record Port(Set<Protocol> protocol, int port, String service) {
}
