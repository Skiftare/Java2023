package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Nested
    class ConnectionTests {
        @Test
        @DisplayName("Тест стабильного соединения")
        void testThatCreateStableConnectionAndReturnedWorkingConnection() throws Exception {
            RandomGenerating.init();
            Connection cnct = new Connection.StableConnection();
            String command = "SomeCommand";
            assertEquals(cnct.execute(command), command);
        }

        @Test
        @DisplayName("Тест нестабильного соединения на разрыв")
        void testThatCreateFaultyConnectionAndReturnedException() throws Exception {
            RandomGenerating.init();
            try (Connection cnct = new Connection.FaultyConnection()) {
                String command = "SomeCommand";
                //Сейчас мы ждём Exception
                Throwable ex = assertThrows(RuntimeException.class, () -> {
                        cnct.execute(command);
                    }
                );
                Assertions.assertEquals("edu.hw2.ConnectionException", ex.toString());
            }

        }

        @Test
        @DisplayName("Тест нестабильного соединения на работу")
        void testThatCreateFaultyConnectionAndReturnedWorkingConnection() throws Exception {
            RandomGenerating.init();
            try (Connection cnct = new Connection.FaultyConnection()) {
                String command = "SomeCommand";
                try {
                    cnct.execute(command);
                } catch (Exception ignored) {
                    //System.out.println("Q");
                }
                try {
                    cnct.execute(command);
                } catch (Exception ignored) {
                    //System.out.println("Q");
                }
                assertEquals(cnct.execute(command), command);
            }
        }
    }
    @Nested
    class ConnectionManagersTests{
        @Test
        @DisplayName("Default Manager test for FaultyConnection")
        void testThatCreateDefaultManagerAndReturnedFaultyConnection() {
            RandomGenerating.init();
            ConnectionManager connManager = new ConnectionManager.DefaultConnectionManager();
            Connection res = connManager.getConnection();
            assertEquals(res.getClass(), Connection.FaultyConnection.class);
        }
        @Test
        @DisplayName("Default Manager test for SuccseccConnection")
        void testThatCreateDefaultManagerAndReturnedStableConnection() {
            RandomGenerating.init();
            ConnectionManager connManager = new ConnectionManager.DefaultConnectionManager();
            Connection res = connManager.getConnection();
            res = connManager.getConnection();
            res = connManager.getConnection();
            assertEquals(res.getClass(), Connection.StableConnection.class);
        }

        @Test
        @DisplayName("Faulty Manager test for FaultyConnection")
        void testThatCreateFaultyManagerAndReturnedFaultyConnection() {
            RandomGenerating.init();
            ConnectionManager connManager = new ConnectionManager.FaultyConnectionManager();
            Connection res = connManager.getConnection();
            assertEquals(res.getClass(), Connection.FaultyConnection.class);
        }
        @Test
        @DisplayName("Faulty Manager test for NOT SuccseccConnection")
        void testThatCreateFaultyManagerAndReturnedNotStableConnection() {
            RandomGenerating.init();
            ConnectionManager connManager = new ConnectionManager.FaultyConnectionManager();
            Connection res = connManager.getConnection();
            res = connManager.getConnection();
            res = connManager.getConnection();
            assertNotEquals(res.getClass(), Connection.StableConnection.class);
        }

    }

    @Nested
    class tryExecTests{
        @Test
        @DisplayName("Удачное выполнение tryExecute с DefaultManager")
        void testThatGetCommandFromDefaultManagerAndReturnedSuccess() throws Exception {
            RandomGenerating.init();
            ConnectionManager manager = new ConnectionManager.DefaultConnectionManager();
            var executor = new PopularCommandExecutor(manager,3);
            executor.tryExecute("DoSmth");
            executor.updatePackages();
        }

        @Test
        @DisplayName("Неудачное выполнение tryExecute с  DefaultManager")
        void testThatGetCommandFromDefaultManagerAndReturnedException() throws Exception {
            RandomGenerating.init();
            ConnectionManager manager = new ConnectionManager.DefaultConnectionManager();
            var executor = new PopularCommandExecutor(manager,1);
            String expected = "edu.hw2.ConnectionException: Connection Exception";
            Throwable ex = assertThrows(RuntimeException.class, () -> {
                executor.tryExecute("DoSmth");
                }
            );
            Assertions.assertEquals(expected, ex.toString());
        }

        @Test
        @DisplayName("Неудачное выполнение tryExecute с FaultyManager")
        void testThatGetCommandFromFaultyManagerAndReturnedException() throws Exception {
            RandomGenerating.init();
            ConnectionManager manager = new ConnectionManager.FaultyConnectionManager();
            var executor = new PopularCommandExecutor(manager,1);
            String expected = "edu.hw2.ConnectionException: Connection Exception";
            Throwable ex = assertThrows(RuntimeException.class, () -> {
                    executor.tryExecute("DoSmth");
                }
            );
            Assertions.assertEquals(expected, ex.toString());
        }

        @Test
        @DisplayName("Удачное выполнение tryExecute с  FaultyManager")
        void testThatGetCommandFromFaultyManagerAndReturnedSuccess() throws Exception {

            RandomGenerating.init();
            ConnectionManager manager = new ConnectionManager.FaultyConnectionManager();
            var executor = new PopularCommandExecutor(manager,3);
            executor.tryExecute("DoSmth");
            executor.updatePackages();
        }

    }
}
