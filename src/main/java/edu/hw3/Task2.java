package edu.hw3;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Task2 {
    private Task2() {
    }

    public static List<StringBuilder> clusterize(String brackets) {
        Objects.requireNonNull(brackets);
        LinkedList<StringBuilder> bracketsCluster = new LinkedList<>();
        bracketsCluster.add(new StringBuilder());
        int openBrackets = 0;
        int closeBrackets = 0;
        for (int i = 0; i < brackets.length() - 1; i++) {
            bracketsCluster.getLast().append(brackets.charAt(i));
            if (brackets.charAt(i) == ')') {
                closeBrackets++;
            } else {
                openBrackets++;
            }
            if (brackets.charAt(i) == ')' && brackets.charAt(i + 1) == '(' && openBrackets == closeBrackets) {
                bracketsCluster.add(new StringBuilder());
                openBrackets = 0;
                closeBrackets = 0;
            }
        }
        bracketsCluster.getLast().append(brackets.charAt(brackets.length() - 1));
        return bracketsCluster;
    }
}
