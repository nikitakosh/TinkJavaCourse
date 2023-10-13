package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void updatePackages(){
        tryExecute("apt update && apt upgrade -y")  ;
    }
    private void tryExecute(String command){
        Connection connection = manager.getConnection();
        for(int i = 0; i < maxAttempts; i++){
            try {
                connection.execute(command);
                connection.close();
                return;
            } catch (ConnectionException e) {
                if (i == maxAttempts-1){
                    throw new ConnectionException((Exception) e.getCause());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
//                connection.close();
            }
        }
    }
}
