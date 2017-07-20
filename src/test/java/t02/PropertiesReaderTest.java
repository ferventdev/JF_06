package t02;

import org.junit.jupiter.api.*;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.*;

class PropertiesReaderTest {
    Properties propsForTests;

    @BeforeEach
    public void setUp() throws Exception {
        try (FileOutputStream f = new FileOutputStream("src/test/resources/forTest.properties")) {
            propsForTests = new Properties();
            propsForTests.setProperty("parameter1", "value1");
            propsForTests.setProperty("parameter2", "value2");
            propsForTests.setProperty("parameter3", "value3");
            propsForTests.store(f, "Property file created for tests");
        }
    }

    @Test
    public void readAllTest() throws Exception {
        PropertiesReader pr = new PropertiesReader();

        assertNull(pr.getProperties());

        pr.readAll("src/test/resources/forTest__x.properties");

        assertNull(pr.getProperties());

        pr.readAll("src/test/resources/forTest.properties");

        Map<Object, Object> prForTests = new HashMap<>();
        prForTests.putAll(propsForTests);
        assertEquals(pr.getProperties(), prForTests);

//        System.out.println(pr.getProperties());
    }

    @Test
    public void readValueTest() throws Exception {
        PropertiesReader pr = new PropertiesReader();

        String value = pr.readValue("src/test/resources/forTest.properties", "parameter1");

        assertThat(value, is(propsForTests.getProperty("parameter1")));

        value = pr.readValue("src/test/resources/forTest.properties", "parameter10");

        assertThat(value, is("No value found for the key parameter10"));
    }
}