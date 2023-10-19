package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Connection connection = manager.getConnection();
        for (int i = 0; i < maxAttempts; i++) {
            try {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts - 1) {
                    throw e;
                }
            }
        }
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
