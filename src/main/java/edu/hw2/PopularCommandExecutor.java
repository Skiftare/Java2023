package edu.hw2;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws Exception {
        //Пытаемся выполнить команду
        for(int currentAttempt = 1;currentAttempt<=maxAttempts;currentAttempt++){
            try{
                manager.getConnection().execute(command);
                return;
            } catch (ConnectionException e) {
                if(currentAttempt == maxAttempts) {
                    throw e;
                }
            }
        }
    }
}
