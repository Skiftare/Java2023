package edu.hw2;


import org.jetbrains.annotations.NotNull;

public interface Connection extends AutoCloseable {
    String execute(String command) throws Exception;
    //Logger LOGGER = LogManager.getLogger();
    //RandomGenerating rnd = new RandomGenerating();
    //String SUCC_EXEC = "successfully executed";

    record StableConnection() implements Connection {
        static boolean isClosed;

        @Override
        public String execute(String command) {
            close();
            return command;
        }

        @Override
        public void close() {
            //LOGGER.info("Closing!");
            isClosed = true;
        }


    }

    record FaultyConnection()  implements Connection {
        static boolean isClosed;

        @Override
        public @NotNull String execute(String command) throws Exception {


            if (RandomGenerating.randomGen() % 2 == 1) {
                close();
                //return "Command + " + command + " unsuccessfully executed";
                throw new ConnectionException();
            }

            close();
            return command;
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

