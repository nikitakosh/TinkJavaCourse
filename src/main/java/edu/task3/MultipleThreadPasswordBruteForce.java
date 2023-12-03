package edu.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleThreadPasswordBruteForce {
    private static final Map<String, String> DATABASE = new ConcurrentHashMap<>(Map.of(
            "e3e84538a1b02b1cc11bf71fe3169958", "a.v.petrov",
            "537daa262cc758ee7ede412f3d73b2d8", "v.v.belv",
            "15bda1664d8a3e8e78f8a054254728ce", "v.v.bel"
    ));
    private static final CountDownLatch LATCH = new CountDownLatch(DATABASE.size());
    private static final Map<String, String> FOUNDED_PASSWORDS = new HashMap<>();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static String getHashMD5(String password) {
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

    public Map<String, String> bruteForce() {
        EXECUTOR_SERVICE.submit(new BruteForceTask(""));
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return FOUNDED_PASSWORDS;
    }

    private record BruteForceTask(String currentPassword) implements Callable<Void> {

        @Override
        public Void call() {
            if (!DATABASE.isEmpty()) {
                String hash = getHashMD5(currentPassword);
                if (DATABASE.containsKey(hash)) {
                    FOUNDED_PASSWORDS.put(DATABASE.get(hash), currentPassword);
                    DATABASE.remove(hash);
                    LATCH.countDown();
                }
                for (char symbol : ALPHABET.toCharArray()) {
                    EXECUTOR_SERVICE.submit(new BruteForceTask(currentPassword + symbol));
                }
            }
            return null;
        }
    }
}
