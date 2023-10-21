package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public interface Connection extends AutoCloseable {
    String execute(String command) throws Exception;
    //Logger LOGGER = LogManager.getLogger();
    //RandomGenerating rnd = new RandomGenerating();


    record StableConnection() implements Connection {
        static boolean isClosed;

        @Override
        public String execute(String command) {
            close();
            return "Command + " + command + " successfully executed";
        }

        @Override
        public void close() {
            //LOGGER.info("Closing!");
            isClosed = true;
        }


    }

    record FaultyConnection()  implements Connection{
        static boolean isClosed;
        @Override
        public @NotNull String execute(String command) throws Exception {


            if(RandomGenerating.RandomGen()%2 == 1){
                close();
                //return "Command + " + command + " unsuccessfully executed";
                throw new ConnectionException();
            }

            close();
            return "Command + " + command + " successfully executed";
        }

        /*@Override
        public void doCommand(String command) {
            //TDO: Прикрутить сюда LOGGER
        }*/

        @Override
        public void close() throws Exception {
            //LOGGER.info("Closing!");
            isClosed = true;
        }


    }

}

