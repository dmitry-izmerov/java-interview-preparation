package ru.demi.tasks;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implement database connection pooling using semaphore.
 */
public class DataBaseConnectionPoolingBySemaphore {

    public static void main(String[] args) {
        DBPool dbPool = new DBPool(2);
        int numThreads = 5;
        for (int i = 1; i <= numThreads; i++) {
            Thread thread = new Thread(new Task(String.valueOf(i), dbPool));
            thread.start();
        }
    }

    private static class DBPool {
        private final Semaphore semaphore;
        private final List<DBConnection> connections;

        DBPool(int size) {
            this.semaphore = new Semaphore(size, true);
            this.connections = IntStream.rangeClosed(1, size)
                .mapToObj(i -> new DBConnection())
                .collect(Collectors.toList());
        }

        DBConnection getConnection() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            DBConnection dbConnection = connections.stream()
                .filter(c -> !c.isBusy())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There no free connections."));

            dbConnection.setBusy(true);
            return dbConnection;
        }

        // just for return to connection pool and release lock
        void closeConnection(DBConnection connection) {
            connection.setBusy(false);
            semaphore.release();
        }
    }

    private static class DBConnection {
        // for simplicity only
        private boolean isBusy;

        boolean isBusy() {
            return isBusy;
        }

        private void setBusy(boolean busy) {
            isBusy = busy;
        }
    }

    private static class Task implements Runnable {
        private static final int TIMEOUT = 5;
        private String id;
        private DBPool dbPool;

        private Task(String id, DBPool dbPool) {
            this.id = id;
            this.dbPool = dbPool;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Task #%s is getting connection.%n", id);
                DBConnection connection = dbPool.getConnection();
                System.out.printf("Task #%s is doing some actions with connection.%n", id);
                TimeUnit.SECONDS.sleep(TIMEOUT);
                System.out.printf("Task #%s is closing connection.%n", id);
                dbPool.closeConnection(connection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
