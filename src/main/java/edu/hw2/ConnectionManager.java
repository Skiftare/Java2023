package edu.hw2;

public interface ConnectionManager {
    Connection getConnection();
    record DefaultConnectionManager() implements ConnectionManager{

        @Override
        public Connection getConnection() {
            return null;
        }
    }

    record FaultyConnectionManager() implements ConnectionManager{

        @Override
        public Connection getConnection() {
            return null;
        }
    }
}
