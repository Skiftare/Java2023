package edu.hw2;

public interface Connection extends AutoCloseable {
    void execute(String command) throws Exception;

    public record StableConnection() implements Connection {

        @Override
        public void execute(String command) throws Exception {
            close();

        }

        @Override
        public void close() throws Exception {
            return ;
        }
    }

    public record FaultyConnection()  implements Connection{

        @Override
        public void execute(String command) throws Exception {
            RandomGenerating rnd = new RandomGenerating();
            if(rnd.RandomGen() == 1){
                close();
                throw new ConnectionException();
            }
            close();
        }

        @Override
        public void close() throws Exception {

        }
    }
}

