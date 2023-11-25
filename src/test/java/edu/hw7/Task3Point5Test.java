package edu.hw7;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task3Point5 Test")
public class Task3Point5Test {
    private static final String PERSON_STR = "Person";
    private static final String ADDRESS_STR = "Address";
    private static final String PHONE_STR = "Phone";

    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new Task3()},
            {new Task3Point5()}
        });
    }

    @DisplayName("Test findByName, findByAddress, findByPhone")
    @ParameterizedTest
    @MethodSource("data")
    public void testThatGetFullPersonDataAndReturnedExpectedSearchInSingleThreads(PersonDatabase task3) {
        //given: database & persons (every person doesn't have null-fields)
        Person person1 = new Person(1, "John Doe", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane Smith", "456 Elm St", "555-5678");
        Person person3 = new Person(3, "Bob Johnson", "789 Oak St", "555-9012");
        //when: we add persons to database
        task3.add(person1);
        task3.add(person2);
        task3.add(person3);

        //then: we can find each one by every field

        // Test findByName
        assertThat(Objects.requireNonNull(task3.findByName("John Doe")).get(0)).isEqualTo(person1);
        assertThat(Objects.requireNonNull(task3.findByName("Jane Smith")).get(0)).isEqualTo(person2);
        assertThat(Objects.requireNonNull(task3.findByName("Bob Johnson")).get(0)).isEqualTo(person3);

        // Test findByAddress
        assertThat(Objects.requireNonNull(task3.findByAddress("123 Main St")).get(0)).isEqualTo(person1);
        assertThat(Objects.requireNonNull(task3.findByAddress("456 Elm St")).get(0)).isEqualTo(person2);
        assertThat(Objects.requireNonNull(task3.findByAddress("789 Oak St")).get(0)).isEqualTo(person3);

        // Test findByPhone
        assertThat(Objects.requireNonNull(task3.findByPhone("555-1234")).get(0)).isEqualTo(person1);
        assertThat(Objects.requireNonNull(task3.findByPhone("555-5678")).get(0)).isEqualTo(person2);
        assertThat(Objects.requireNonNull(task3.findByPhone("555-9012")).get(0)).isEqualTo(person3);
    }


    @DisplayName("Test concurrency with multiple threads")
    @ParameterizedTest
    @MethodSource("data")
    public void testThatGetFullPersonDataAndReturnedExpectedSearchInMultiThreads(PersonDatabase task3) throws InterruptedException {
        //given: database & persons in threads (every person doesn't have null-fields)
        int countOfThreads = 20;
        int countOfPersonsAtIteration = 90;

        Thread[] threads = new Thread[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                for (int j = 1; j <= countOfPersonsAtIteration; j++) {
                    String pseudoHash = Integer.toString(j+finalI*countOfPersonsAtIteration);
                    Person person = new Person(Integer.parseInt(pseudoHash), PERSON_STR + pseudoHash, ADDRESS_STR + pseudoHash, PHONE_STR + pseudoHash);
                    //when: we add persons to database
                    task3.add(person);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        //then: we can find each one by every field
        for (int i = 0; i < countOfThreads; i++) {
            for (int j = 1; j <= countOfPersonsAtIteration; j++) {
                String pseudoHash = Integer.toString(j+i*countOfPersonsAtIteration);

                assertThat(task3.findByName(PERSON_STR + pseudoHash)).isNotEmpty();
                assertThat(Objects.requireNonNull(task3.findByName(PERSON_STR + pseudoHash)).size()).isEqualTo(1);

                assertThat(task3.findByAddress(ADDRESS_STR + pseudoHash)).isNotNull();
                assertThat(Objects.requireNonNull(task3.findByAddress(ADDRESS_STR + pseudoHash)).size()).isEqualTo(1);

                assertThat(task3.findByPhone(PHONE_STR + pseudoHash)).isNotNull();
                assertThat(Objects.requireNonNull(task3.findByPhone(PHONE_STR + pseudoHash)).size()).isEqualTo(1);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("Test finding people with null fields")
    public void testThatGetNotFullPersonDataAndReturnedNoResult(PersonDatabase task3) {
        //given: database and person with null-fields

        Person person1 = new Person(1, null, null, null);
        Person person2 = new Person(2, "John Doe", null, null);
        Person person3 = new Person(3, null, "123 Main St", null);
        Person person4 = new Person(4, null, null, "555-1234");

        //when: we add person
        task3.add(person1);
        task3.add(person2);
        task3.add(person3);
        task3.add(person4);

        //thn: we can't find them, in case of null-fields
        assertThat(task3.findByName(person2.getName())).isEmpty();
        assertThat(task3.findByAddress(person3.getAddress())).isEmpty();
        assertThat(task3.findByPhone(person4.getPhoneNumber())).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("Test finding people with null fields")
    public void testThatGetNotFullPersonDataButWithMergingAndReturnedResults(PersonDatabase task3) {
        //given: database and person with null-fields
        Person person1 = new Person(1, null, null, null);
        Person person2 = new Person(2, "John Doe", null, null);
        Person person3 = new Person(3, null, "123 Main St", null);
        Person person4 = new Person(4, null, null, "555-1234");

        task3.add(person1);
        task3.add(person2);
        task3.add(person3);
        task3.add(person4);

        //when: we create & add updatedPersons (Obj with same id merge with the priority with new data)
        Person updatedPerson1 = new Person(1, "Jane Smith", "456 Elm St", "555-5678");
        Person updatedPerson2 = new Person(2, "John Doe", "789 Oak St", "555-9012");
        Person updatedPerson3 = new Person(3, "Bob Johnson", "123 Main St", "555-12345");
        Person updatedPerson4 = new Person(4, "Alice Brown", "456 Elm St", "555-5678");

        task3.add(updatedPerson1);
        task3.add(updatedPerson2);
        task3.add(updatedPerson3);
        task3.add(updatedPerson4);

        //then: we can find them (all is not-null), and null-query don't lay down system
        assertThat(task3.findByName(null)).isEmpty();
        assertThat(task3.findByAddress(null)).isEmpty();
        assertThat(task3.findByPhone(null)).isEmpty();

        assertThat(Objects.requireNonNull(task3.findByName(updatedPerson1.getName())).get(0)).isEqualTo(updatedPerson1);
        assertThat(Objects.requireNonNull(task3.findByAddress(updatedPerson2.getAddress())).get(0)).isEqualTo(updatedPerson2);
        assertThat(Objects.requireNonNull(task3.findByPhone(updatedPerson3.getPhoneNumber())).get(0)).isEqualTo(updatedPerson3);
        assertThat(Objects.requireNonNull(task3.findByName(updatedPerson4.getName())).get(0)).isEqualTo(updatedPerson4);
    }
}
