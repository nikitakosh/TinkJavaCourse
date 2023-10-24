package edu.hw3;


public class Task5 {

    private static final String ASC = "asc";
    private static final String DESC = "desc";

    private Task5() {
    }

    public static String[] parseContacts(String[] fullNames, String sortType) {
        if (!sortType.equalsIgnoreCase(ASC) && !sortType.equalsIgnoreCase(DESC)) {
            throw new InvalidSortTypeException("invalid sort type");
        }
        if (fullNames == null) {
            return new String[]{};
        }
        int l = 0;
        int r = fullNames.length - 1;
        quickSort(fullNames, l, r, sortType);
        return fullNames;
    }

    public static void quickSort(String[] fullNames, int begin, int end, String sortType) {
        if (begin < end) {
            String pivot = fullNames[end];
            int i = (begin - 1);

            for (int j = begin; j < end; j++) {
                if (sortType.equalsIgnoreCase(ASC)) {
                    if (fullNames[j].split(" ")[1].compareTo(pivot.split(" ")[1]) <= 0) {
                        i++;
                        String swapTemp = fullNames[i];
                        fullNames[i] = fullNames[j];
                        fullNames[j] = swapTemp;
                    }
                } else if (sortType.equalsIgnoreCase(DESC)) {
                    if (fullNames[j].split(" ")[1].compareTo(pivot.split(" ")[1]) >= 0) {
                        i++;
                        String swapTemp = fullNames[i];
                        fullNames[i] = fullNames[j];
                        fullNames[j] = swapTemp;
                    }
                }
            }
            int partitionIndex = i + 1;
            String swapTemp = fullNames[i + 1];
            fullNames[i + 1] = fullNames[end];
            fullNames[end] = swapTemp;
            quickSort(fullNames, begin, partitionIndex - 1, sortType);
            quickSort(fullNames, partitionIndex + 1, end, sortType);
        }
    }
}
