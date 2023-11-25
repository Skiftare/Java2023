package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task3Point5 Test")
public class Task3Point5Test {
    private static final String PERSON_STR = "Person";
    private static final String ADDRESS_STR = "Address";
    private static final String PHONE_STR = "Phone";
    @Test
    @DisplayName("Test findByName, findByAddress, findByPhone")
    public void testThatGetFullPersonDataAndReturnedExpectedSearchInSingleThreads() {
        Task3Point5 task3 = new Task3Point5();

        Person person1 = new Person(1, "John Doe", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane Smith", "456 Elm St", "555-5678");
        Person person3 = new Person(3, "Bob Johnson", "789 Oak St", "555-9012");

        task3.add(person1);
        task3.add(person2);
        task3.add(person3);

        // Test findByName
        assertThat(task3.findByName("John Doe").get(0)).isEqualTo(person1);
        assertThat(task3.findByName("Jane Smith").get(0)).isEqualTo(person2);
        assertThat(task3.findByName("Bob Johnson").get(0)).isEqualTo(person3);

        // Test findByAddress
        assertThat(task3.findByAddress("123 Main St").get(0)).isEqualTo(person1);
        assertThat(task3.findByAddress("456 Elm St").get(0)).isEqualTo(person2);
        assertThat(task3.findByAddress("789 Oak St").get(0)).isEqualTo(person3);

        // Test findByPhone
        assertThat(task3.findByPhone("555-1234").get(0)).isEqualTo(person1);
        assertThat(task3.findByPhone("555-5678").get(0)).isEqualTo(person2);
        assertThat(task3.findByPhone("555-9012").get(0)).isEqualTo(person3);
    }

    @Test
    @DisplayName("Test concurrency with multiple threads")
    public void testThatGetFullPersonDataAndReturnedExpectedSearchInMultiThreads() throws InterruptedException {
        Task3Point5 task3 = new Task3Point5();

        // Create and start multiple threads
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    Person person = new Person(j*100+ finalI, PERSON_STR + j, ADDRESS_STR + j, PHONE_STR + j);
                    task3.add(person);
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Test find methods
        for (int i = 0; i < 100; i++) {
            Person person = task3.findByName(PERSON_STR + i).get(0);
            assertThat(person).isNotNull();
            assertThat(person.getName()).isEqualTo(PERSON_STR + i);
            assertThat(person.getAddress()).isEqualTo(ADDRESS_STR + i);
            assertThat(person.getPhoneNumber()).isEqualTo(PHONE_STR + i);

            person = task3.findByAddress(ADDRESS_STR + i).get(0);
            assertThat(person).isNotNull();
            assertThat(person.getName()).isEqualTo(PERSON_STR + i);
            assertThat(person.getAddress()).isEqualTo(ADDRESS_STR + i);
            assertThat(person.getPhoneNumber()).isEqualTo(PHONE_STR + i);

            person = task3.findByPhone(PHONE_STR + i).get(0);
            assertThat(person).isNotNull();
            assertThat(person.getName()).isEqualTo(PERSON_STR + i);
            assertThat(person.getAddress()).isEqualTo(ADDRESS_STR + i);
            assertThat(person.getPhoneNumber()).isEqualTo(PHONE_STR + i);
        }
    }

    @Test
    @DisplayName("Test finding people with null fields")
    public void testThatGetNotFullPersonDataAndReturnedNoResult() {
        Task3Point5 task3 = new Task3Point5();

        Person person1 = new Person(1, null, null, null);
        Person person2 = new Person(2, "John Doe", null, null);
        Person person3 = new Person(3, null, "123 Main St", null);
        Person person4 = new Person(4, null, null, "555-1234");

        task3.add(person1);
        task3.add(person2);
        task3.add(person3);
        task3.add(person4);

        // Test find methods before data is updated
        assertThat(task3.findByName(person2.getName())).isNull();
        assertThat(task3.findByAddress(person3.getAddress())).isNull();
        assertThat(task3.findByPhone(person4.getPhoneNumber())).isNull();
    }

    @Test
    @DisplayName("Test finding people with null fields")
    public void testThatGetNotFullPersonDataButWithMergingAndReturnedResults() {
        Task3Point5 task3 = new Task3Point5();

        Person person1 = new Person(1, null, null, null);
        Person person2 = new Person(2, "John Doe", null, null);
        Person person3 = new Person(3, null, "123 Main St", null);
        Person person4 = new Person(4, null, null, "555-1234");

        task3.add(person1);
        task3.add(person2);
        task3.add(person3);
        task3.add(person4);

        // Update data
        Person updatedPerson1 = new Person(1, "Jane Smith", "456 Elm St", "555-5678");
        Person updatedPerson2 = new Person(2, "John Doe", "789 Oak St", "555-9012");
        Person updatedPerson3 = new Person(3, "Bob Johnson", "123 Main St", "555-12345");
        Person updatedPerson4 = new Person(4, "Alice Brown", "456 Elm St", "555-5678");

        task3.add(updatedPerson1);
        task3.add(updatedPerson2);
        task3.add(updatedPerson3);
        task3.add(updatedPerson4);

        // Test find methods after data is updated
        assertThat(task3.findByName(null)).isNull();
        assertThat(task3.findByAddress(null)).isNull();
        assertThat(task3.findByPhone(null)).isNull();

        assertThat(task3.findByName(updatedPerson1.getName()).get(0)).isEqualTo(updatedPerson1);
        assertThat(task3.findByAddress(updatedPerson2.getAddress()).get(0)).isEqualTo(updatedPerson2);
        assertThat(task3.findByPhone(updatedPerson3.getPhoneNumber()).get(0)).isEqualTo(updatedPerson3);
        assertThat(task3.findByName(updatedPerson4.getName()).get(0)).isEqualTo(updatedPerson4);
    }
}
