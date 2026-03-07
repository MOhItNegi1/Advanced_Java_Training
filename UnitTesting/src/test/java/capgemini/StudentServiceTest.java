package capgemini;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

public class StudentServiceTest {

    Calculator c;

    @BeforeEach
    void setUp() {
        c = new Calculator();
    }

    @Test
    void testAdd() {
        Assertions.assertEquals(10, c.add(6, 4));
        Assertions.assertNotEquals(20, c.add(5, 8));
    }

    @Test
    void testSub() {
        Assertions.assertEquals(5, c.sub(15, 10));
    }

    @Test
    void testEven() {
        Assertions.assertTrue(c.even(6));
        Assertions.assertFalse(c.even(5));
    }

    @Test
    void testDivide() {
        Assertions.assertThrows(ArithmeticException.class, () -> c.divide(5, 0));
    }

    @Test
    void checkSetName() {
        Assertions.assertNotNull(c.getName("Name"));
    }

    @Test
    void checkSetNameNull() {
        Assertions.assertNull(c.getName(null));
    }

    @Test
    void checkSame() {
        Assertions.assertSame("OK", c.getName("OK"));
    }

    @Test
    void checkNotSame() {
        Assertions.assertNotSame("ok", c.getName("OK"));
    }

    @ParameterizedTest
    @CsvSource({
        "2,3,5",
        "5,7,12",
        "200,100,300",
        "120,150,270"
    })
    void testParameterized(int a, int b, int expected) {
        Assertions.assertEquals(expected, c.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
        "8,5,3",
        "12,8,4",
        "10,22,-12"
    })
    void testParameterizedSub(int a, int b, int expected) {
        Assertions.assertEquals(expected, c.sub(a, b));
    }

    @ParameterizedTest
    @CsvSource({
        "3, false",
        "4, true",
        "8, true"
    })
    void testParameterizedEven(int a, boolean expected) {
        Assertions.assertEquals(expected, c.even(a));
    }

    @ParameterizedTest
    @CsvFileSource(files = "C:/Users/mohit/Documents/Copy of add.csv", numLinesToSkip = 0)
        void testParameterizedADD(int a, int b, int expected){
        Assertions.assertEquals(expected, c.add(a,b));
}




}
