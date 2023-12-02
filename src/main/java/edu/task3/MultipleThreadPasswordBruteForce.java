package edu.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleThreadPasswordBruteForce {
    private static final Map<String, String> DATABASE = new ConcurrentHashMap<>(Map.of(
            "a256e6b336afdc38c564789c399b516c", "a.v.petrov",
            "0f98df87c7440c045496f705c7295344", "v.v.belov"
    ));
    private static final CountDownLatch LATCH = new CountDownLatch(DATABASE.size());
    private static final Map<String, String> FOUNDED_PASSWORDS = new ConcurrentHashMap<>();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(4);


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
        EXECUTOR_SERVICE.submit(new MyThread(""));
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return FOUNDED_PASSWORDS;
    }

    private record MyThread(String currentPassword) implements Callable<Void> {

        @Override
        public Void call() {
            if (DATABASE.isEmpty()) {
                return null;
            }
            String hash = getHashMD5(currentPassword);
            if (DATABASE.containsKey(hash)) {
                FOUNDED_PASSWORDS.put(DATABASE.get(hash), currentPassword);
                DATABASE.remove(hash);
                LATCH.countDown();
            }
            for (char symbol : ALPHABET.toCharArray()) {
                EXECUTOR_SERVICE.submit(new MyThread(currentPassword + symbol));
            }
            return null;
        }
    }
}
