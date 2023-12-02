package edu.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SingleThreadPasswordBruteForce {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    Map<String, String> database = new HashMap<>(Map.of(
            "a256e6b336afdc38c564789c399b516c", "a.v.petrov",
            "0f98df87c7440c045496f705c7295344", "v.v.belov"
    ));

    public String getHashMD5(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(password.getBytes());
        byte[] md5Bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : md5Bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public Map<String, String> singleThreadBruteForce() {
        Map<String, String> foundedPasswords = new HashMap<>();
        int currentLength = 1;
        while (true) {
            int[] indices = new int[currentLength];
            boolean isAllFound = false;
            while (true) {
                String currentPassword = nextPassword(indices);
                String hash = getHashMD5(currentPassword);
                if (database.containsKey(hash)) {
                    foundedPasswords.put(database.get(hash), currentPassword);
                    database.remove(hash);
                }
                if (database.isEmpty()) {
                    isAllFound = true;
                    break;
                }
                int i = currentLength - 1;
                while (i >= 0 && indices[i] == alphabet.length() - 1) {
                    indices[i] = 0;
                    i--;
                }

                if (i < 0) {
                    break;
                }
                indices[i]++;
            }
            if (isAllFound) {
                break;
            }
            currentLength++;
        }
        return foundedPasswords;
    }


    private String nextPassword(int[] indices) {
        StringBuilder currentPassword = new StringBuilder();
        for (int index : indices) {
            currentPassword.append(alphabet.charAt(index));
        }
        return currentPassword.toString();
    }
}
