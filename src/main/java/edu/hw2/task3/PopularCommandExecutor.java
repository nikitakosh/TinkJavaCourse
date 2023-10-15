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

        for(int i = 0; i < maxAttempts; i++){
                try (Connection connection = manager.getConnection();){
                    connection.execute(command);
                    return;
                } catch (ConnectionException e) {
                    if (i == maxAttempts-1){
                        throw new ConnectionException("number of attempts exceeded");
                    }
                }
        }
    }
}
