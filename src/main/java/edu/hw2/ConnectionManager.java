package edu.hw2;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface ConnectionManager {
    Connection getConnection();
    record DefaultConnectionManager() implements ConnectionManager{

        @Contract(pure = true) @Override
        public @NotNull Connection getConnection() {


            Connection newConnection = new Connection.StableConnection();
            if(RandomGenerating.RandomGen()%2 == 1){
                newConnection = new Connection.FaultyConnection();
            }

            return newConnection;

        }

    }

    record FaultyConnectionManager() implements ConnectionManager{

        @Override
        public @NotNull Connection getConnection() {
            Connection.FaultyConnection newConnection = new Connection.FaultyConnection();
            return newConnection;
        }
    }
}
